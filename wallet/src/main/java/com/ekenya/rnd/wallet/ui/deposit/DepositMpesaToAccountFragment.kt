package com.ekenya.rnd.wallet.ui.deposit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.common.abstractions.BaseBottomSheetDialogFragment
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentDepositMpesaToAccountWalletBinding
import com.ekenya.rnd.wallet.utils.BaseWalletBottomSheetDialogFragment
import com.ekenya.rnd.wallet.utils.createSuccessBundle
import com.ekenya.rnd.wallet.utils.setTransparentBackground
import com.ekenya.rnd.wallet.utils.timber
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.HashMap

class DepositMpesaToAccountFragment :
    BaseWalletBottomSheetDialogFragment<FragmentDepositMpesaToAccountWalletBinding>(
        FragmentDepositMpesaToAccountWalletBinding::inflate
    ) {

    private var hashMap: HashMap<String, String> = HashMap()

    override fun onResume() {
        super.onResume()
        val budgetMonths = resources.getStringArray(R.array.my_account)
        val arrayAdapterMonths = ArrayAdapter(
            requireContext(), R.layout.text_layout_wallet, budgetMonths
        )
        binding.actSelectAccount.setAdapter(arrayAdapterMonths)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = getString(R.string.mpesa_to_account_wallet),
                subTitle = getString(R.string.mpesa_to_account_wallet),
                cardTitle = getString(R.string.mpesa_to_account_wallet),
                cardContent = getString(R.string.cardContent_wallet),
                hashMap = hashMap
            )
        )
        setUpUI()

    }

    private fun setUpUI() {
        setOnCheckedChangeListener()
        setTransparentBackground()
    }

    private fun setOnCheckedChangeListener() {
        binding.radioGroupBankOption.setOnCheckedChangeListener { group, checkedId ->
            var text = ""
            if (R.id.radioButtonMyBank == checkedId) {
                binding.tIlSelectAccount.isVisible = true
                binding.tILayoutAccountNumber.isVisible = false
                text = getString(R.string.my_bank_wallet)
            } else if (R.id.radioButtonOtherBank == checkedId) {
                binding.tIlSelectAccount.isVisible = false
                binding.tILayoutAccountNumber.isVisible = true
                text = getString(R.string.other_bank_wallet)
            } else {
                text = getString(R.string.my_bank_wallet)
            }
            Timber.e(text)
        }
        setUi()
    }


    private fun setUi() {
        timber(binding.edtPhoneNumber.text.toString())

        binding.btnContinue.setOnClickListener {

            if (addValidations()) {

                hashMap["Amount"] =
                    " ${binding.edtAmount.text.toString()}"
                hashMap["PhoneNumber"] = " ${binding.edtPhoneNumber.text.toString()}"
                hashMap["Deposit to"] = binding.actSelectAccount.text.toString()

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        getString(R.string.Confirm_Mpesa_to_Account_deposit),
                        getString(R.string.Please_confirm_you_are_making_an_Mpesa_to_Account_deposit),
                        hashMap,
                        dialogCallback
                    )
                    //dismiss()
                }


            }

        }

        if (binding.radioButtonMyBank.isChecked) {
            if (binding.actSelectAccount.text.isNullOrEmpty()
                || binding.actSelectAccount.text.isNullOrBlank()
            ) {
                //binding.actSelectAccount.error = getString(R.string.enter_all_field_wallet)

            }
        }
        if (binding.radioButtonOtherBank.isChecked) {
            if (binding.edtAccountNumber.text.isNullOrEmpty()
                || binding.edtAccountNumber.text.isNullOrBlank()
            ) {
                // binding.edtAccountNumber.error = getString(R.string.enter_all_field_wallet)

            }
        }


    }

    private fun addValidations(): Boolean {
        if (binding.edtPhoneNumber.text.isNullOrEmpty() || binding.edtPhoneNumber.text.isNullOrBlank()) {
            binding.edtPhoneNumber.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.edtAmount.text.isNullOrEmpty()
            || binding.edtAmount.text.isNullOrBlank()
        ) {
            binding.edtAmount.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }

    companion object {

        fun newInstance() =
            DepositMpesaToAccountFragment()
    }
}