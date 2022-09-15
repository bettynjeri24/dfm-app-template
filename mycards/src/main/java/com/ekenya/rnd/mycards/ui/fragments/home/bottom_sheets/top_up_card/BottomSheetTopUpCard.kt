package com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.top_up_card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.common.abstractions.BaseBottomSheetDialogFragment
import com.ekenya.rnd.mycards.ui.dialog.alertDialog.showAlertDialog
import com.ekenya.rnd.mycards.R
import com.ekenya.rnd.mycards.databinding.BottomSheetTopUpCardsBinding
import com.ekenya.rnd.common.dialogs.base.adapter_detail.model.DialogDetailCommon
import com.ekenya.rnd.common.form_validation.ui_extensions.formatAsCurrency
import com.ekenya.rnd.common.form_validation.ui_extensions.isNotEmpty
import com.ekenya.rnd.common.form_validation.ui_extensions.validateForm
import com.ekenya.rnd.mycards.ui.fragments.home.MyCardsHomeFragment
import com.ekenya.rnd.mycards.utils.setUpSpinner
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BottomSheetTopUpCard(private val navigateToAuth: () -> Unit) : BaseBottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetTopUpCardsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetTopUpCardsBinding.inflate(inflater,container,false)
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
        binding.textinputEditTextAmount.formatAsCurrency()

        val validators = listOf(
            binding.textinputEditTextAmount.isNotEmpty(),
            binding.spinnerAccount.isNotEmpty()
        )

        binding.button.validateForm(validators = validators)
    }

    private fun setUpBindings() {
        binding.apply {
            binding.spinnerAccount.setUpSpinner(R.array.accountProvider, onItemClick = {parent, view, position, id ->

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
            setDialogTitle("Confirm Card Top-Up")

            // set sub title
            setDialogSubtitle("Please confirm you want to top-up your gold credit Card ${binding.spinnerAccount.text}")

            // add dialog details
            setUpRecyclerAdapter(getDetails())

            // add execution logic to confirm button click
            confirmButtonClickListener {
                MyCardsHomeFragment.apply {
                    title = "Card Top-Up Successful"
                    subtitle = "Your card top-up has been received successfully and is being processed"
                    cardTitle = "Pay From"
                    cardText = "Gold credit 1234 •••• •••• 2344"
                    details = getDetails().apply {
                        add(DialogDetailCommon("TIME & DATE:", "12-04-2021 | 4:00PM"))
                        add(DialogDetailCommon("REF ID:", "1234567890"))
                    }
                }
                lifecycleScope.launch {
                    simulateSearching()
                    navigateToAuth.invoke()
                    this@BottomSheetTopUpCard.dismiss()
                }
            }

            cancelButtonClickListener {

            }


        }

        //  show the dialog
        merchantPaymentDialog.show()
    }

    private fun getDetails() = mutableListOf<DialogDetailCommon>().apply {
        add(DialogDetailCommon("Amount", binding.textinputEditTextAmount.text.toString()))
        add(DialogDetailCommon("Top Up from", "Current Account- A/C #${binding.spinnerAccount.text}"))
        add(DialogDetailCommon("Charges", "Kes 522.06"))
    }

    private suspend fun simulateSearching(){
        showHideProgress("Topping Up card")
        delay(2000)
        showHideProgress(null)
    }

}