package com.ekenya.rnd.wallet.ui.fundtransfer.requestfunds.mpesatoaccount

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentMpesaToAccountWalletBinding
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap


class MpesaToAccountFragmentWallet :
    BaseWalletBottomSheetDialogFragment<FragmentMpesaToAccountWalletBinding>(
        FragmentMpesaToAccountWalletBinding::inflate
    ) {
    private var hashMap: HashMap<String, String> = HashMap()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(view.parent as View).setBackgroundColor(Color.TRANSPARENT)

        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = getString(R.string.withdraw_from_agent_wallet),
                subTitle = getString(R.string.withdraw_from_agent_wallet),
                cardTitle = getString(R.string.withdraw_from_agent_wallet),
                cardContent = getString(R.string.cardContent_wallet),
                hashMap = hashMap
            )
        )
        setUi()


    }


    private fun setUi() {
        setTransparentBackground()
        binding.actAccountType.setUpSpinner(
            R.array.accountTypeWallet,
            onItemClick = { parent, view, pos, id ->
                val selectedItem = parent?.adapter?.getItem(pos).toString()
                Log.e("SPINNER", selectedItem)
            })


        binding.btnContinue.setOnClickListener {
            if (addValidations()) {

                hashMap["Phone Number"] =
                    " ${binding.edtPhoneNumber.text.toString()}"
                hashMap["Amount"] = " ${binding.edtAmount.text.toString()}"
                hashMap["Transfer From"] = binding.actAccountType.text.toString()

                lifecycleScope.launch {
                    showHideProgress("Sending request")
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        "Fund Transfer",
                        "EFT",
                        hashMap,
                        dialogCallback
                    )
                    dismiss()
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
        if (binding.actAccountType.text.isNullOrEmpty() || binding.actAccountType.text.isNullOrBlank()) {
            binding.actAccountType.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }


}