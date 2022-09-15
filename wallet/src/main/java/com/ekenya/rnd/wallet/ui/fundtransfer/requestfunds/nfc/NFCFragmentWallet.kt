package com.ekenya.rnd.wallet.ui.fundtransfer.requestfunds.nfc

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentNfcWalletBinding
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap


class NFCFragmentWallet :
    BaseWalletBottomSheetDialogFragment<FragmentNfcWalletBinding>(
        FragmentNfcWalletBinding::inflate
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


        binding.btnContinue.setOnClickListener {
            if (addValidations()) {

                hashMap["Phone Number"] =
                    " ${binding.edtCardNumber.text.toString()}"
                hashMap["Amount"] = " ${binding.edtAmount.text.toString()}"
                hashMap["Transfer From"] = binding.edtCardNumber.text.toString()

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
        if (binding.edtCardNumber.text.isNullOrEmpty()
            || binding.edtCardNumber.text.isNullOrBlank()
        ) {
            binding.edtCardNumber.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        else {
            return true
        }
    }


}