package com.ekenya.rnd.wallet.ui.fundtransfer.pesalink.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentPesalinkSettingsWalletBinding
import com.ekenya.rnd.wallet.utils.BaseWalletFragment
import com.ekenya.rnd.wallet.utils.createSuccessBundle
import com.ekenya.rnd.wallet.utils.setBackButton
import com.ekenya.rnd.wallet.utils.showDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap


class PesaLinkSettingsFragmentWallet :
    BaseWalletFragment<FragmentPesalinkSettingsWalletBinding>(FragmentPesalinkSettingsWalletBinding::inflate) {

    private var hashMap: HashMap<String, String> = HashMap()

    companion object {
        fun newInstance() =
            PesaLinkSettingsFragmentWallet()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = getString(R.string.pesalink_settings_wallet),
                subTitle = getString(R.string.pesalink_settings_wallet),
                cardTitle = getString(R.string.deregister_account_wallet),
                cardContent = getString(R.string.cardContent_wallet),
                hashMap = hashMap
            )
        )

        setUpUi()
    }

    private fun setUpUi() {
        binding.clToolBar.toolbar.setBackButton(
            R.string.pesalink_settings_wallet,
            requireActivity()
        )

        binding.cvDeregisterAccount.setOnClickListener {
            deregisterAccount()

        }

        binding.cvUnlinkPhone.setOnClickListener {
            deregisterAccount()
        }
    }

    private fun deregisterAccount() {
        hashMap["Deregister Account:"] = getString(R.string.current_account_hint)

        requireContext().showDialog(
            title = getString(R.string.deregister_account_wallet),
            description = getString(
                R.string.Would_you_like_to_deregister_your_account_from_Pesalink_wallet
            ),
            positiveButtonFunction = {
                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        "${getString(R.string.deregister_account_wallet)} ",
                        getString(R.string.Would_you_like_to_deregister_your_account_from_Pesalink_wallet),
                        hashMap,
                        dialogCallback
                    )
                }

            },
            negativeButtonFunction = {}
        )
    }


}