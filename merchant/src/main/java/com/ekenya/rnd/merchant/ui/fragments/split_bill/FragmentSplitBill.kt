package com.ekenya.rnd.merchant.ui.fragments.split_bill

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.merchant.R
import com.ekenya.rnd.merchant.databinding.FragmentSplitBillMerchantBinding
import com.ekenya.rnd.merchant.ui.fragments.direct_payment.FragmentDirectPayment
import com.ekenya.rnd.merchant.ui.fragments.direct_payment.bottom_sheet_merchant_payment.BottomSheetMerchantPayment
import com.ekenya.rnd.merchant.ui.fragments.select_contact.FragmentSelectContact
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.DaggerFragment
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.flow.MutableStateFlow

class FragmentSplitBill : DaggerFragment() {

    lateinit var binding: FragmentSplitBillMerchantBinding
    private val shouldSplitBill: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplitBillMerchantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    private fun setUpUI() {
        setUpBindings()
        lifecycleScope.launchWhenResumed {
            setUpSelectedContactsAdapter()
        }
    }

    private fun setUpSelectedContactsAdapter() {
        binding.recyclerViewSelected.apply {

            //initialize selected contacts adapter
            val selectedAdapter =
                AdapterSplitBillContact(shouldSplitBill, sendCustomerAmount = { before, after ->
                    respondToAmountChanges(before, after)
                })

            //set the adapter
            adapter = selectedAdapter

            //set a linear layout manager
            layoutManager = LinearLayoutManager(requireContext())

            //Bad practice... Will fix this.
            selectedAdapter.submitList(FragmentSelectContact.contactHashSet.toList().also {
                if (it.isNotEmpty()) {
                    binding.groupMerchantDetails.isVisible = true
                }
            })

            // add a touch helper
            setUpCustomItemTouchHelper(selectedAdapter)

        }

    }

    //set up view bindings here
    private fun setUpBindings() {

        binding.apply {

            //button to add more people to split the bill
            buttonAddMorePeople.setOnClickListener {
                findNavController(this@FragmentSplitBill).navigate(R.id.action_splitBill_to_SelectContact)
            }

            // button to launch bottom sheet payment
            buttonContinue.setOnClickListener {
                showMerchantPaymentBottomSheet()
            }

            //determine if the bill should be split
            binding.switchSplitBill.setOnCheckedChangeListener { _, shouldSplit ->
                val amount  = if (shouldSplit) (FragmentDirectPayment.amount / (FragmentSelectContact.contactHashSet.size + 1)) else FragmentDirectPayment.amount
                binding.include.textViewEqualAmount.text = amount.toString()
                shouldSplitBill.value = shouldSplit
            }


            binding.apply {
                toolbar.setNavigationOnClickListener {
                    //navigate up
                    findNavController(this@FragmentSplitBill).navigateUp()
                }
            }

            binding.include.textViewEqualAmount.text = FragmentDirectPayment.amount.toString()

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        FragmentSelectContact.contactHashSet.clear()
    }

    //Allows a user to swipe to delete and add a custom background
    private fun setUpCustomItemTouchHelper(selectedAdapter: AdapterSplitBillContact) {
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
                    FragmentSelectContact.contactHashSet.toMutableList().apply {
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
                                R.color.accent
                            )
                        )
                        .addActionIcon(R.drawable.ic_swipe_delete_merchant)
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

    // show the payment bottom sheet
    private fun showMerchantPaymentBottomSheet() {
        val bottomSheetDialogFragment: BottomSheetDialogFragment =
            BottomSheetMerchantPayment (toSuccess = {navigateToSuccess()}, toSelectContact = {})
        bottomSheetDialogFragment.show(
            requireActivity().supportFragmentManager,
            bottomSheetDialogFragment.tag
        )
    }

    private fun navigateToSuccess() {
        findNavController().navigate(R.id.action_splitBillFragment_to_successfulFragment)
    }

    private fun respondToAmountChanges(previousAmount: Int, currentAmount: Int): Boolean {

        // get previous amount from the textview
        val currentSelfAmount = binding.include.textViewEqualAmount.text.toString().toInt()

        // get the total amount of money they are about to share
        val totalAmount = currentSelfAmount + previousAmount

        // get the remainder
        val remainder = totalAmount - currentAmount

        // if new user amount is about to overflow, reverse everything
        if (remainder <= 0){
            // give self previous amount
            binding.include.textViewEqualAmount.text = currentSelfAmount.toString()

            // give feedback to viewHolder to also reverse
            return true
        }

        // assign remaining amount to self
        binding.include.textViewEqualAmount.text = remainder.toString()

        // inform viewHolder their amount is within the limits of total money
        return false
    }

}