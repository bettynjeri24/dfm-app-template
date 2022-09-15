package com.ekenya.rnd.wallet.ui.myaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.dialogs.dialog_confirm.ConfirmDialogCallBacks
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentWithdrawMyAccountWalletBinding
import com.ekenya.rnd.wallet.utils.BaseWalletFragment
import com.ekenya.rnd.wallet.utils.createSuccessBundle
import com.ekenya.rnd.wallet.utils.setBackButton
import com.ekenya.rnd.wallet.utils.timber
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap


class WithdrawMyAccountWalletFragment :
    BaseWalletFragment<FragmentWithdrawMyAccountWalletBinding>(
    FragmentWithdrawMyAccountWalletBinding::inflate
) {
    private var hashMap: HashMap<String, String> = HashMap()

    override fun onResume() {
        super.onResume()
        val getStringArray = resources.getStringArray(R.array.mno_mobile_money_providers_Wallet)
        val arrayAdapter = ArrayAdapter(
            requireContext(), R.layout.text_layout_wallet, getStringArray
        )
        binding.actSelectAccount.setAdapter(arrayAdapter)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clToolBar.toolbar.setBackButton(
            (R.string.withdraw_wallet),
            requireActivity()
        )

        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = getString(R.string.withdraw_wallet),
                subTitle = getString(R.string.withdraw_wallet),
                cardTitle = getString(R.string.withdraw_wallet),
                cardContent = getString(R.string.cardContent_wallet),
                hashMap = hashMap
            )
        )
        setUpUi()


    }

    private fun setUpUi() {
        binding.btnContinue.setOnClickListener {
            if (addValidations()) {

                hashMap["Phone Number:"] = binding.edtPhoneNumber.text.toString()
                hashMap["AMOUNT:"] = binding.edtAmount.text.toString()
                hashMap["Withdraw to:"] = binding.actSelectAccount.text.toString()

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        "${getString(R.string.withdraw_wallet)} ",
                        getString(R.string.withdraw_wallet),
                        hashMap,
                        dialogCallback
                    )
                }
            }

        }
    }


    private fun addValidations(): Boolean {
        if (binding.edtAmount.text.isNullOrEmpty() || binding.edtAmount.text.isNullOrBlank()) {
            binding.edtAmount.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.edtPhoneNumber.text.isNullOrEmpty()
            || binding.edtPhoneNumber.text.isNullOrBlank()
        ) {
            binding.edtPhoneNumber.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.actSelectAccount.text.isNullOrEmpty() || binding.actSelectAccount.text.isNullOrBlank()) {
            binding.actSelectAccount.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }

    companion object {
        fun newInstance() =
            WithdrawMyAccountWalletFragment()
    }


}