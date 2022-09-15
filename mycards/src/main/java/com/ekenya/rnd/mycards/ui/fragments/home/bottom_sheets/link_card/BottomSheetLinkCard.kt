package com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.link_card

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.common.abstractions.BaseBottomSheetDialogFragment
import com.ekenya.rnd.common.code_scanner.AnyOrientationCaptureActivity
import com.ekenya.rnd.common.dialogs.base.adapter_detail.model.DialogDetailCommon
import com.ekenya.rnd.common.form_validation.ui_extensions.isNotEmpty
import com.ekenya.rnd.common.form_validation.ui_extensions.validateForm
import com.ekenya.rnd.common.form_validation.ui_extensions.validateMinimumLength
import com.ekenya.rnd.mycards.R
import com.ekenya.rnd.mycards.data.room.entities.CardEntity
import com.ekenya.rnd.mycards.databinding.BottomSheetLinkcardCardsBinding
import com.ekenya.rnd.mycards.ui.dialog.alertDialog.showAlertDialog
import com.ekenya.rnd.mycards.ui.fragments.home.MyCardsHomeFragment
import com.ekenya.rnd.mycards.utils.setUpSpinner
import com.google.zxing.client.android.Intents
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class BottomSheetLinkCard(private val navigateToAuth: () -> Unit) : BaseBottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetLinkcardCardsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[LinkCardViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetLinkcardCardsBinding.inflate(inflater, container, false)
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
            binding.spinnerCardType.isNotEmpty(),
            binding.textinputEditTextCardNumber.isNotEmpty(),
            binding.textinputEditTextCardNumber.validateMinimumLength(16),
            binding.textInputEditTextCVV.validateMinimumLength(3),
            binding.textInputEditTextExpiryDate.isNotEmpty()
        )

        binding.button.validateForm(validators = validators)
    }

    private fun setUpBindings() {
        binding.apply {

            binding.spinnerCardType.setUpSpinner(
                R.array.creditType,
                onItemClick = { parent, view, pos, id ->
                    onCardSelected(parent, view, pos, id)
                })

            binding.button.setOnClickListener {
                showDialog()
            }

            binding.imageViewScan.setOnClickListener {
                scanCustomScanner()
            }

        }
    }

    private fun linkCard() {
        val cardType = binding.spinnerCardType.text.toString();
        val cardNumber = binding.textinputEditTextCardNumber.text.toString();
        val expiryDate = binding.textInputEditTextExpiryDate.text.toString();
        val cvv = binding.textInputEditTextCVV.text.toString();
        val card = CardEntity(
            0, cardType,"Visa",cardNumber,cvv
        )
        viewModel.addCards(card)
    }

    private fun onCardSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //do something
    }

    private fun showDialog() {

        val merchantPaymentDialog = showAlertDialog {

            // not cancellable
            cancelable = false

            // set title
            setDialogTitle("Confirm link Card")

            // set sub title
            setDialogSubtitle("Please confirm you want to link your ${binding.spinnerCardType.text} Card 1234 •••• •••• 2344")

            // add dialog details
            setUpRecyclerAdapter(getDetails())

            // add execution logic to confirm button click
            confirmButtonClickListener {

                MyCardsHomeFragment.apply {
                    title = "Link Card"
                    subtitle = "Your card was linked successfully"
                    cardTitle = "Card linked For"
                    cardText = "Gold credit 1234 •••• •••• 2344"
                }
                MyCardsHomeFragment.details = getDetails().apply {
                    add(DialogDetailCommon("TIME & DATE:", "12-04-2021 | 4:00PM"))
                }

                // simulate loading
                lifecycleScope.launch {
                    simulateSearching()
                    linkCard()
                    navigateToAuth()
                    this@BottomSheetLinkCard.dismiss()
                }

            }

            cancelButtonClickListener {

            }

        }

        //  show the dialog
        merchantPaymentDialog.show()
    }

    private fun scanCustomScanner() {
        val options = ScanOptions()
        options.setOrientationLocked(false)
        options.captureActivity = AnyOrientationCaptureActivity::class.java
        barcodeLauncher.launch(options)
    }

    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            val originalIntent = result.originalIntent
            if (originalIntent == null) {
                Log.d("MainActivity", "Cancelled scan")
                Toast.makeText(
                    requireContext(),
                    "Cancelled",
                    Toast.LENGTH_LONG
                ).show()
            } else if (originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                Log.d(
                    "MainActivity",
                    "Cancelled scan due to missing camera permission"
                )
                Toast.makeText(
                    requireContext(),
                    "Cancelled due to missing camera permission",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Log.d("MainActivity", "Scanned")
            Toast.makeText(
                requireContext(),
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun getDetails() = mutableListOf<DialogDetailCommon>().apply {

        add(DialogDetailCommon("CARD TYPE:", binding.spinnerCardType.text.toString()))
        add(DialogDetailCommon("CARD NUMBER:", binding.textinputEditTextCardNumber.text.toString()))
        add(DialogDetailCommon("EXPIRY DATE:", binding.textInputEditTextExpiryDate.text.toString()))
        add(DialogDetailCommon("CVV:", binding.textInputEditTextCVV.text.toString()))

    }

    private suspend fun simulateSearching(){
        showHideProgress("Linking Cards")
        delay(2000)
        showHideProgress(null)
    }

}