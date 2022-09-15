package com.ekenya.rnd.wallet.ui.fundtransfer.pesalink.toaccount

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.auth.AuthResult
import com.ekenya.rnd.common.dialogs.dialog_confirm.ConfirmDialogCallBacks
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentPesalinkToAccountWalletBinding
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap


class PesalinkToAccountFragmentWallet : BaseWalletFragment<FragmentPesalinkToAccountWalletBinding>(
    FragmentPesalinkToAccountWalletBinding::inflate
) {
    private var hashMap: HashMap<String, String> = HashMap()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setBackButton(R.string.pesa_link_to_account_wallet, requireActivity())


        setFragmentResultListener("requestKey") { _, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result: AuthResult = bundle.get("authResult") as AuthResult

            when (result) {

                AuthResult.AUTH_SUCCESS -> {

                    findNavController().navigate(
                        R.id.successfulFragmentWallet,
                        createSuccessBundle()
                    )

                }
                AuthResult.AUTH_ERROR -> {
                    //handle error
                }
            }

        }

        binding.apply {
            actAccountType.setUpSpinner(
                R.array.accountTypeWallet,
                onItemClick = { parent, view, pos, id ->
                    val selectedItem = parent?.adapter?.getItem(pos).toString()
                    Log.e("SPINNER", selectedItem)
                })

            actSelectBank.setUpSpinner(
                R.array.banksWallet,
                onItemClick = { parent, view, pos, id ->
                    val selectedItem = parent?.adapter?.getItem(pos).toString()
                    Log.e("SPINNER", selectedItem)

                })
        }

        binding.btnContinue.setOnClickListener {
            if (addValidations()) {

                hashMap["Phone Number:"] = " ${binding.edtRecipientAccountNumber.text.toString()}"
                hashMap["AMOUNT:"] = "Kes ${binding.edtAmount.text.toString()}"
                hashMap["TRANSFER FROM:"] = binding.actAccountType.text.toString()
                hashMap["BANK:"] = binding.actSelectBank.text.toString()
                hashMap["ACCOUNT NAME:"] = binding.edtRecipientName.text.toString()
                hashMap["CHARGES:"] = getString(R.string.kes_0_00_wallet)

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        getString(R.string.Confirm_Pesalink_to_Phone),
                        getString(
                            R.string.Please_confirm_you_are_making_an_Pesalink_to_Phone_254712345678
                        ),
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
        if (binding.edtRecipientAccountNumber.text.isNullOrEmpty() || binding.edtRecipientAccountNumber.text.isNullOrBlank()) {
            binding.edtRecipientAccountNumber.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.actAccountType.text.isNullOrEmpty() || binding.actAccountType.text.isNullOrBlank()) {
            binding.actAccountType.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }

    private fun createSuccessBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString("title", getString(R.string.pesa_link_to_account_wallet))
        bundle.putString("subtitle", getString(R.string.pesa_link_to_account_wallet))
        bundle.putString("cardTitle", getString(R.string.phone_number_wallet))
        bundle.putString("cardContent", binding.edtRecipientAccountNumber.text.toString())

        hashMap["Phone Number:"] = " ${binding.edtRecipientAccountNumber.text.toString()}"
        hashMap["AMOUNT:"] = "Kes ${binding.edtAmount.text.toString()}"
        hashMap["TRANSFER FROM:"] = binding.actAccountType.text.toString()
        hashMap["BANK:"] = binding.actSelectBank.text.toString()
        hashMap["ACCOUNT NAME:"] = binding.edtRecipientName.text.toString()
        hashMap["CHARGES:"] = getString(R.string.kes_0_00_wallet)

        bundle.putSerializable("content", hashMap)

        return bundle
    }

}