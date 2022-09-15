package com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.change_pin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseBottomSheetDialogFragment
import com.ekenya.rnd.common.form_validation.ui_extensions.*
import com.ekenya.rnd.mycards.databinding.BottomSheetChangePinCardsBinding
import com.ekenya.rnd.mycards.ui.dialog.alertDialog.showChangePinDialog
import com.ekenya.rnd.mycards.ui.fragments.home.MyCardsHomeFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BottomSheetChangePin(private val navigateToAuth: () -> Unit) : BaseBottomSheetDialogFragment() {


    private lateinit var binding: BottomSheetChangePinCardsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetChangePinCardsBinding.inflate(inflater, container, false)
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
            binding.textinputEditTextOldPin.isNotEmpty(),
            binding.textinputEditTextOldPin.validateMinimumLength(4),

            binding.textinputEditTextNewPin.isNotEmpty(),
            binding.textinputEditTextNewPin.validateMinimumLength(4),
            binding.textinputEditTextNewPin.isValidPin(),

            binding.textinputEditTextConfirmPin.passwordsMatch(binding.textinputEditTextNewPin)
        )

        binding.button.validateForm(validators = validators)
    }

    private fun setUpBindings() {

        binding.button.setOnClickListener {
           showDialog()
        }
    }

    // show dialog
    private fun showDialog() {

        val changePinDialogDetail = showChangePinDialog {

            // not cancellable
            cancelable = false

            // add execution logic to confirm button click
            confirmButtonClickListener {
                MyCardsHomeFragment.apply {
                    title = "Change Pin"
                    subtitle = "You have successfully changed your card pin"
                    cardTitle = "Change Pin For"
                    cardText = "Gold credit 1234 •••• •••• 2344"
                }
                // simulate loading
                lifecycleScope.launch {
                    simulateSearching()
                    navigateToAuth.invoke()
                    this@BottomSheetChangePin.dismiss()
                }
            }

            cancelButtonClickListener {

            }


        }

        //  show the dialog
        changePinDialogDetail.show()
    }

    private suspend fun simulateSearching(){
        showHideProgress("Sending Change Pin request")
        delay(2000)
        showHideProgress(null)
    }


}