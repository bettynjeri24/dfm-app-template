package com.ekenya.rnd.wallet.ui.fundtransfer.banktransfers.owntransfer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentOwnTransferWalletBinding
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap


class OwnTransferFragmentWallet :
    BaseWalletFragment<FragmentOwnTransferWalletBinding>(FragmentOwnTransferWalletBinding::inflate) {
    private var hashMap: HashMap<String, String> = HashMap()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = getString(R.string.own_transfer_wallet),
                subTitle = getString(R.string.own_transfer_wallet),
                cardTitle = getString(R.string.own_transfer_wallet),
                cardContent = getString(R.string.cardContent_wallet),
                hashMap = hashMap
            )
        )

        setUi()
    }

    private fun setUi() {
        binding.toolbar.setBackButton(R.string.own_transfer_wallet, requireActivity())
        binding.apply {
            aCtSelectAccount.setUpSpinner(
                R.array.accountTypeWallet,
                onItemClick = { parent, view, pos, id ->
                    val selectedItem = parent?.adapter?.getItem(pos).toString()
                    Log.e("SPINNER", selectedItem)
                })
        }

        binding.btnContinue.setOnClickListener {
            if (addValidations()) {

                hashMap["Recipient Account"] =
                    " ${binding.edTRecipientAccountNumber.text.toString()}"
                hashMap["Amount"] = " ${binding.edtAmount.text.toString()}"
                hashMap["Transfer From"] = binding.aCtSelectAccount.text.toString()

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        getString(R.string.own_transfer_wallet),
                        getString(R.string.own_transfer_wallet),
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
        if (binding.edTRecipientAccountNumber.text.isNullOrEmpty()
            || binding.edTRecipientAccountNumber.text.isNullOrBlank()
        ) {
            binding.edTRecipientAccountNumber.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.aCtSelectAccount.text.isNullOrEmpty() || binding.aCtSelectAccount.text.isNullOrBlank()) {
            binding.aCtSelectAccount.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }


}