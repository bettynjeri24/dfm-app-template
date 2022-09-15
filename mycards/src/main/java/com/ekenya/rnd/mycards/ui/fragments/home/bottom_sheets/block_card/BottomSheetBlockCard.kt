package com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.block_card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.common.abstractions.BaseBottomSheetDialogFragment
import com.ekenya.rnd.common.auth.CommonAuthFragment
import com.ekenya.rnd.common.dialogs.base.adapter_detail.model.DialogDetailCommon
import com.ekenya.rnd.common.form_validation.ui_extensions.isNotEmpty
import com.ekenya.rnd.common.form_validation.ui_extensions.validateForm
import com.ekenya.rnd.mycards.R
import com.ekenya.rnd.mycards.databinding.BottomSheetBlockCardsBinding
import com.ekenya.rnd.mycards.ui.dialog.alertDialog.showAlertDialog
import com.ekenya.rnd.mycards.ui.fragments.home.MyCardsHomeFragment
import com.ekenya.rnd.mycards.utils.setUpSpinner
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BottomSheetBlockCard (private val navigateToAuth: () -> Unit) : BaseBottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetBlockCardsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetBlockCardsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    private fun setUpUi() {
        setUpBindings()
        setUpFormValidation()
    }

    private fun setUpFormValidation() {
        val validators = listOf(
            binding.autoCompleteReason.isNotEmpty(),
            binding.autoCompleteAccount.isNotEmpty()
        )

        binding.button.validateForm(validators = validators)
    }

    private fun setUpBindings() {
        binding.apply {
            
            autoCompleteReason.setUpSpinner(R.array.reasonsProviders, onItemClick = {parent, view, position, id ->  })
            
            autoCompleteAccount.setUpSpinner(R.array.accountProvider, onItemClick = { parent, view, position, id ->

            })
        }
        binding.button.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {

        val merchantPaymentDialog = showAlertDialog {

            // not cancellable
            cancelable = false

            // set title
            setDialogTitle("Confirm Block Card")

            // set sub title
            setDialogSubtitle("Please confirm you want to block your gold credit Card 1234 •••• •••• 2344")

            // add dialog details
            setUpRecyclerAdapter(getDetails())

            // add execution logic to confirm button click
            confirmButtonClickListener {

                MyCardsHomeFragment.apply {
                    title = "Block Card"
                    subtitle = "Your card was blocked successfully"
                    cardTitle = "Card Blocked For"
                    cardText = "Gold credit 1234 •••• •••• 2344"
                }
                MyCardsHomeFragment.details = getDetails().apply {
                    add(DialogDetailCommon("TIME & DATE:", "Kes 522.06"))
                }

                // simulate loading
                lifecycleScope.launch {

                    simulateSearching()
                    navigateToAuth.invoke()
                    this@BottomSheetBlockCard.dismiss()

                }

            }

            cancelButtonClickListener {

            }


        }

        //  show the dialog
        merchantPaymentDialog.show()
    }

    private suspend fun simulateSearching(){
        showHideProgress("Sending blocking Card request")
        delay(2000)
        showHideProgress(null)
    }


    private fun getDetails() = mutableListOf<DialogDetailCommon>().apply {
        add(DialogDetailCommon("REASON:", binding.autoCompleteReason.text.toString()))
        add(DialogDetailCommon("ACCOUNT:", "Current Account- A/C #${binding.autoCompleteAccount.text}"))
    }

}