package com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.selectcontacts

import android.graphics.Canvas
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentSplitMobileMoenyWalletBinding
import com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.adapter.AdapterContentChanged
import com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.adapter.AdapterSplitMobileMoneyContact
import com.ekenya.rnd.wallet.utils.*
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.HashMap

class FragmentSplitSendToMobileMoneyWallet :
    BaseWalletFragment<FragmentSplitMobileMoenyWalletBinding>(
        FragmentSplitMobileMoenyWalletBinding::inflate
    ) {

    // Initialize Variables
    private lateinit var selectedAdapter: AdapterSplitMobileMoneyContact
    private val equalAmount = MutableStateFlow<Int>(0)
    private var hashMap: HashMap<String, String> = HashMap()
    var OLDTOTALAMOUNT = 1

    override fun onResume() {
        super.onResume()
        val budgetMonths = resources.getStringArray(R.array.account_type_wallet)
        val arrayAdapterMonths = ArrayAdapter(
            requireContext(),
            R.layout.text_layout_wallet,
            budgetMonths
        )
        binding.aCtSelectAccount.setAdapter(arrayAdapterMonths)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = getString(R.string.mobile_money_wallet),
                subTitle = getString(R.string.mobile_money_wallet),
                cardTitle = SUCCESSFUL_CARDTITLE,
                cardContent = SUCCESSFUL_CARDCONTENT,
                hashMap = hashMap

            )
        )
        infalteLayout()
    }

    private fun infalteLayout() {
        // set ui for views
        setUpBindings()
        lifecycleScope.launchWhenResumed {
            setUpSelectedContactsAdapter()
        }
        binding.switchSendToMany.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Timber.e("")
                binding.textInputAmount.isVisible = true
            } else {
                binding.textInputAmount.isVisible = false
            }
        }
    }

    /*
    * set Up for selected contacts
    */
    private fun setUpSelectedContactsAdapter() {
        val contactList = FragmentSelectContactWallet.contactHashSet.toList().also {
            if (it.isNotEmpty()) {
                // binding.groupMerchantDetails.isVisible = true
            }
        }
        binding.recyclerViewSelected.apply {
            // initialize selected contacts adapter
            selectedAdapter =
                AdapterSplitMobileMoneyContact(
                    equalAmount = equalAmount,
                    adapterContentChanged = adapterContentChanged
                )
            // set the adapter
            adapter = selectedAdapter
            // set a linear layout manager
            layoutManager = LinearLayoutManager(requireContext())
            // Bad practice... Will fix this.
            selectedAdapter.submitList(contactList)

            // setUpCustomItemTouchHelper(selectedAdapter)
        }
    }

    // set up view bindings here
    private fun setUpBindings() {
        binding.clToolBar.toolbar.setBackButton(
            title = R.string.send_money_to_mpesa_wallet,
            context = requireActivity(),
            action = { findNavController().navigateUp() }
        )

        binding.edtAmount.addTextChangedListener(object : TextWatcher {
            // keep track of the previous amount

            // get previous amount and track it
            override fun beforeTextChanged(string: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val amount = if (!string.isNullOrEmpty()) string.toString().toInt() else 0
                OLDTOTALAMOUNT = amount
            }

            // get new value and send the to the fragment for further processing
            override fun onTextChanged(
                newValue: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                val newEqualAmount =
                    if (!newValue.isNullOrEmpty()) newValue.toString().toInt() else 0
                equalAmount.value = newEqualAmount
            }

            override fun afterTextChanged(p0: Editable?) {
                // do nothing
            }
        })
        // button to launch bottom sheet payment
        binding.buttonContinue.setOnClickListener {
            if (addValidations()) {
                hashMap["Total Amount:"] = " ${binding.buttonContinue.text}"
                // hashMap["Transfer From:"] = binding.aCtSelectAccount.text.toString()

                SUCCESSFUL_CARDTITLE = getString(R.string.account_name_wallet)
                SUCCESSFUL_CARDCONTENT = getString(R.string.current_account_wallet)

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        getString(R.string.Confirm_Send_to_Mpesa),
                        getString(
                            R.string.Please_confirm_you_are_making_an_Send_to_Mpesa_to
                        ),
                        hashMap,
                        dialogCallback
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        FragmentSelectContactWallet.contactHashSet.clear()
    }

    val adapterContentChanged = object : AdapterContentChanged {
        override fun valuesChanged(sum: Int) {
            if (sum == 0) {
                binding.buttonContinue.text = "Total Kes:  $sum"
                binding.buttonContinue.isEnabled = false
            } else {
                binding.buttonContinue.isEnabled = true
                binding.buttonContinue.text = "Total Kes:  $sum"
            }
        }
    }

    // ------------------------------------------------------------------------------------------
    // Allows a user to swipe to delete and add a custom background
    private fun setUpCustomItemTouchHelper(selectedAdapter: AdapterSplitMobileMoneyContact) {
        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(
                    0,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.layoutPosition
                    FragmentSelectContactWallet.contactHashSet.toMutableList().apply {
                        removeAt(position)
                        selectedAdapter.submitList(this)
                    }
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    RecyclerViewSwipeDecorator.Builder(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                        .addBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.accent_blue
                            )
                        )
                        .addActionIcon(R.drawable.ic_round_add_wallet)
                        .create()
                        .decorate()

                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }
            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewSelected)
    }

    private fun addValidations(): Boolean {
        /* if (binding.edtAmount.text.isNullOrEmpty() || binding.edtAmount.text.isNullOrBlank()) {
             binding.edtAmount.error = getString(R.string.enter_all_field_wallet)
             return false
         }
         if (binding.edtPhoneNumber.text.isNullOrEmpty() || binding.edtPhoneNumber.text.isNullOrBlank()) {
             binding.edtPhoneNumber.error = getString(R.string.enter_all_field_wallet)
             return false
         }
       */
        if (binding.aCtSelectAccount.text.isNullOrEmpty() || binding.aCtSelectAccount.text.isNullOrBlank()) {
            binding.aCtSelectAccount.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }
}
