package com.ekenya.rnd.wallet.ui.withdraw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentWithdrawFromAgentWalletBinding
import com.ekenya.rnd.wallet.utils.BaseWalletFragment
import com.ekenya.rnd.wallet.utils.createSuccessBundle
import com.ekenya.rnd.wallet.utils.setBackButton
import com.ekenya.rnd.wallet.utils.timber
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap


class WithdrawFromAgentFragmentWallet : BaseWalletFragment<FragmentWithdrawFromAgentWalletBinding>(
    FragmentWithdrawFromAgentWalletBinding::inflate
) {
    private var hashMap: HashMap<String, String> = HashMap()
    override fun onResume() {
        super.onResume()
        val budgetMonths = resources.getStringArray(R.array.my_account)
        val arrayAdapterMonths = ArrayAdapter(
            requireContext(), R.layout.text_layout_wallet, budgetMonths
        )
        binding.aCtSelectAccount.setAdapter(arrayAdapterMonths)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        binding.clToolBar.toolbar.setBackButton(R.string.withdraw_from_agent, requireActivity())

        binding.btnContinue.setOnClickListener {
            if (addValidations()) {

                hashMap["Agent Number"] =
                    " ${binding.edtAgentNumber.text.toString()}"
                hashMap["Amount"] = " ${binding.edtAmount.text.toString()}"
                hashMap["Withdraw from"] = binding.aCtSelectAccount.text.toString()

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        getString(R.string.ConfirmWithdrawfromAgent),
                        getString(R.string.PleaseconfirmyouarewithdrawingfromEclecticsAgent),
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
        if (binding.edtAgentNumber.text.isNullOrEmpty()
            || binding.edtAgentNumber.text.isNullOrBlank()
        ) {
            binding.edtAgentNumber.error = getString(R.string.enter_all_field_wallet)
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