package com.ekenya.rnd.wallet.ui.fundtransfer.banktransfers.rtgs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentRtgsWalletBinding
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap


class RtgsFragmentWallet :
    BaseWalletFragment<FragmentRtgsWalletBinding>(FragmentRtgsWalletBinding::inflate) {
    private var hashMap: HashMap<String, String> = HashMap()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = getString(R.string.rtgs_wallet),
                subTitle = getString(R.string.rtgs_wallet),
                cardTitle = getString(R.string.rtgs_wallet),
                cardContent = getString(R.string.cardContent_wallet),
                hashMap = hashMap
            )
        )

        setUi()
    }


    private fun setUi() {
        binding.apply {
            clToolBar.toolbar.setBackButton(R.string.rtgs_wallet, requireActivity())

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

            actSelectBranch.setUpSpinner(
                R.array.branchesWallet,
                onItemClick = { parent, view, pos, id ->
                    val selectedItem = parent?.adapter?.getItem(pos).toString()
                    Log.e("SPINNER", selectedItem)
                })

        }

        binding.btnContinue.setOnClickListener {
            if (addValidations()) {

                hashMap["Recipient Account"] =
                    " ${binding.edtRecipientAccountNumber.text.toString()}"
                hashMap["Amount"] = " ${binding.edtAmount.text.toString()}"
                hashMap["Transfer From"] = binding.actAccountType.text.toString()

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        getString(R.string.rtgs_wallet),
                        getString(R.string.rtgs_wallet),
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
        if (binding.actAccountType.text.isNullOrEmpty() || binding.actAccountType.text.isNullOrBlank()) {
            binding.actAccountType.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }
}


