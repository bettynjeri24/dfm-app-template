package com.ekenya.rnd.wallet.ui.fundtransfer.banktransfers.internaltransfer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentInternalTransferWalletBinding
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.HashMap


class InternalTransferFragmentWallet :
    BaseWalletFragment<FragmentInternalTransferWalletBinding>(
        FragmentInternalTransferWalletBinding::inflate
    ) {
    private var hashMap: HashMap<String, String> = HashMap()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = getString(R.string.internal_transfer_wallet),
                subTitle = getString(R.string.internal_transfer_wallet),
                cardTitle = getString(R.string.internal_transfer_wallet),
                cardContent = getString(R.string.cardContent_wallet),
                hashMap = hashMap
            )
        )
        setUi()
    }

    private fun setUi() {
        binding.apply {
            toolbar.setBackButton(R.string.internal_transfer_wallet, requireActivity())

            actSelectAccount.setUpSpinner(
                R.array.accountTypeWallet,
                onItemClick = { parent, view, pos, id ->
                    val selectedItem = parent?.adapter?.getItem(pos).toString()
                    Timber.e("SPINNER %s", selectedItem)
                })
        }
        binding.btnContinue.setOnClickListener {
            if (addValidations()) {

                hashMap["Recipient Account"] =
                    " ${binding.edtRecipientAccountNumber.text.toString()}"
                hashMap["Amount"] = " ${binding.edtAmount.text.toString()}"
                hashMap["Transfer From"] = binding.actSelectAccount.text.toString()

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        getString(R.string.internal_transfer_wallet),
                        getString(R.string.internal_transfer_wallet),
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