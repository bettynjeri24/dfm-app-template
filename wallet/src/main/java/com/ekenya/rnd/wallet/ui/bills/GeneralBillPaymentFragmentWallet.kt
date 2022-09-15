package com.ekenya.rnd.wallet.ui.bills

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.BillsWalletModel
import com.ekenya.rnd.wallet.databinding.FragmentZukuInternetWalletBinding
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.HashMap


class GeneralBillPaymentFragmentWallet :
    BaseWalletFragment<FragmentZukuInternetWalletBinding>(FragmentZukuInternetWalletBinding::inflate) {

    private var isAccountNumberEntered = true
    private var hashMap: HashMap<String, String> = HashMap()
    private lateinit var billsWalletModel: BillsWalletModel
    override fun onResume() {
        super.onResume()

        binding.actSelectAccount.setUpSpinner(
            R.array.accountTypeWallet,
            onItemClick = { parent, view, pos, id ->
                val selectedItem = parent?.adapter?.getItem(pos).toString()
                Timber.e("SPINNER", selectedItem)
            })
        binding.actSelectFrequency.setUpSpinner(
            R.array.frequency_types,
            onItemClick = { parent, view, pos, id ->
                val selectedItem = parent?.adapter?.getItem(pos).toString()
                Timber.e("SPINNER", selectedItem)
            })


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            billsWalletModel =
                bundle.getParcelable("BillsWalletModel")!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = SUCCESSFUL_TITLE,
                subTitle = SUCCESSFUL_SUBTITLE,
                cardTitle = SUCCESSFUL_CARDTITLE,
                cardContent = SUCCESSFUL_CARDCONTENT,
                hashMap = hashMap
            )
        )
        setUpUI()
    }

    private fun setUpUI() {


        binding.imgBillLogo.setImageResource(billsWalletModel.imageBills)
        binding.clToolBar.toolbar.setBackButton(billsWalletModel.titleBills, requireActivity())

        if (isAccountNumberEntered) {
            binding.edtAccountNumber.isFocusable = true
            binding.clZukuPresentment.isVisible = false
        } else {
            binding.clZukuPresentment.isVisible = true
            binding.edtAccountNumber.isFocusable = false
        }

        binding.btnContinue.setOnClickListener {
            if (binding.edtAccountNumber.text.toString().isNotEmpty() && !addValidations()) {
                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    binding.clZukuPresentment.isVisible = true
                    binding.edtAccountNumber.isFocusable = false
                    binding.edtAccountName.isFocusable = false
                    binding.edtAccountName.setText(R.string.john_doe_wallet)
                    isAccountNumberEntered = !isAccountNumberEntered
                }
            } else if (binding.edtAccountNumber.text.toString().isEmpty()) {
                binding.edtAccountNumber.error = getString(R.string.enter_all_field_wallet)
            } else {
                if (addValidations() && binding.edtAccountNumber.text.toString().isNotEmpty()) {

                    hashMap["CHARGES:"] = getString(R.string.kes_0_00_wallet)
                    hashMap["Account:"] = binding.actSelectAccount.text.toString()
                    hashMap["Amount:"] = binding.edtAmount.text.toString()
                    hashMap["Account Number:"] = binding.edtAccountNumber.text.toString()
                    //hashMap["Frequency:"] = binding.actSelectFrequency.text.toString()
                    hashMap["Account Name:"] = binding.edtAccountName.text.toString()

                    SUCCESSFUL_SUBTITLE =
                        getString(R.string.Your_transaction_was_successful_and_is_being_processed_now)
                    SUCCESSFUL_CARDTITLE = getString(R.string.pay_from_wallet)
                    SUCCESSFUL_CARDCONTENT = binding.actSelectAccount.text.toString()

                    lifecycleScope.launch {
                        showHideProgress(getString(R.string.sending_request_wallet))
                        delay(2000)
                        showHideProgress(null)
                        showConfirmationDialog(
                            getString(R.string.confirm_wallet, billsWalletModel.titleBills),
                            getString(
                                R.string.Please_confirm_you_are_paying_for,
                                billsWalletModel.titleBills,
                                binding.edtAccountNumber.text.toString()
                            ),
                            hashMap,
                            dialogCallback
                        )
                    }
                }
            }
        }

        //Switch
        if (binding.switchSchedule.isChecked) {
            binding.textInputFrequency.isVisible = true
            binding.textInputNextDate.isVisible = true
        } else {
            binding.textInputFrequency.isVisible = false
            binding.textInputNextDate.isVisible = false
        }
        binding.switchSchedule.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.textInputFrequency.isVisible = true
                binding.textInputNextDate.isVisible = true
            } else {
                binding.textInputFrequency.isVisible = false
                binding.textInputNextDate.isVisible = false
            }


        }


    }

    private fun addMeterNumberValidation(): Boolean {
        if (binding.edtAccountNumber.text.isNullOrEmpty() ||
            binding.edtAccountNumber.text.isNullOrBlank()
        ) {
            binding.edtAccountNumber.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }

    private fun addValidations(): Boolean {
        if (binding.edtAmount.text.isNullOrEmpty() ||
            binding.edtAmount.text.isNullOrBlank()
        ) {
            binding.edtAmount.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.edtAccountName.text.isNullOrEmpty() ||
            binding.edtAccountName.text.isNullOrBlank()
        ) {
            binding.edtAccountName.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.actSelectAccount.text.isNullOrEmpty() || binding.actSelectAccount.text.isNullOrBlank()) {
            binding.actSelectAccount.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }

}