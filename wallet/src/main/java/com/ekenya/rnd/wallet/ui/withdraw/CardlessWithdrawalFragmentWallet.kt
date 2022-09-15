package com.ekenya.rnd.wallet.ui.withdraw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentCardlessWithdrawalWalletBinding
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap


class CardlessWithdrawalFragmentWallet :
    BaseWalletFragment<FragmentCardlessWithdrawalWalletBinding>(
        FragmentCardlessWithdrawalWalletBinding::inflate
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
                title = getString(R.string.card_less_withdrawal_wallet),
                subTitle = getString(R.string.card_less_withdrawal_wallet),
                cardTitle = getString(R.string.card_less_withdrawal_wallet),
                cardContent = getString(R.string.cardContent_wallet),
                hashMap = hashMap
            )
        )

        setUi()
    }


    private fun setUi() {
        binding.clToolBar.toolbar.setBackButton(R.string.card_less_withdrawal, requireActivity())

        binding.btnContinue.setOnClickListener {
            if (addValidations()) {

                hashMap["Amount"] =
                    " ${binding.edtAmount.text.toString()}"
                hashMap["PhoneNumber"] = " ${binding.edtPhoneNumber.text.toString()}"
                hashMap["Withdraw from"] = binding.actSelectAccount.text.toString()

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        getString(R.string.ConfirmCardLessWithdrawal),
                        getString(R.string.Pleaseconfirmyouaremakingacardlesswithdrawal),
                        hashMap,
                        dialogCallback
                    )
                }
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
        }
        if (binding.actSelectAccount.text.isNullOrEmpty() || binding.actSelectAccount.text.isNullOrBlank()) {
            binding.actSelectAccount.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }
}