package com.ekenya.rnd.wallet.ui.fundtransfer.banktransfers.eft

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.auth.AuthResult
import com.ekenya.rnd.common.dialogs.dialog_confirm.ConfirmDialogCallBacks
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentEftWalletBinding
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap


class EftFragmentWallet :
    BaseWalletFragment<FragmentEftWalletBinding>(FragmentEftWalletBinding::inflate) {
    private var hashMap: HashMap<String, String> = HashMap()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = getString(R.string.Confirm_EFT_Transaction),
                subTitle = getString(R.string.PleaseconfirmyouaremakinganEFTTransactiontoEquityBank),
                cardTitle = getString(R.string.phone_number_wallet),
                cardContent = binding.edtRecipientAccountNumber.text.toString(),
                hashMap = hashMap
            )
        )

        binding.apply {
            clToolBar.toolbar.setBackButton(R.string.eft_wallet, requireActivity())

            //select account spinner
            actSelectAccount.setUpSpinner(
                R.array.account_type_wallet,
                onItemClick = { parent, view, pos, id ->
                    val selectedItem = parent?.adapter?.getItem(pos).toString()
                    Log.e("SPINNER", selectedItem)
                })
            //select bank spinner
            actSelectBank.setUpSpinner(
                R.array.banks_wallet,
                onItemClick = { parent, view, pos, id ->
                    val selectedItem = parent?.adapter?.getItem(pos).toString()
                    Log.e("SPINNER", selectedItem)
                })
            //select branch spinner
            actSelectBranch.setUpSpinner(
                R.array.branchesWallet,
                onItemClick = { parent, view, pos, id ->
                    val selectedItem = parent?.adapter?.getItem(pos).toString()
                    Log.e("SPINNER", selectedItem)
                })
        }
        setUi()
    }

    private fun setUi() {

        binding.btnContinue.setOnClickListener {
            if (addValidations()) {

                hashMap["Phone Number:"] = " ${binding.edtRecipientAccountNumber.text.toString()}"
                hashMap["AMOUNT:"] = "Kes ${binding.edtAmount.text.toString()}"
                hashMap["TRANSFER FROM:"] = binding.actSelectAccount.text.toString()
                hashMap["BANK:"] = binding.actSelectBank.text.toString()
                hashMap["ACCOUNT NAME:"] = binding.edtRecipientName.text.toString()
                hashMap["CHARGES:"] = getString(R.string.kes_0_00_wallet)

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        getString(R.string.Confirm_EFT_Transaction),
                        getString(R.string.PleaseconfirmyouaremakinganEFTTransactiontoEquityBank),
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
        if (binding.edtRecipientAccountNumber.text.isNullOrEmpty()
            || binding.edtRecipientAccountNumber.text.isNullOrBlank()
        ) {
            binding.edtRecipientAccountNumber.error = getString(R.string.enter_all_field_wallet)
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