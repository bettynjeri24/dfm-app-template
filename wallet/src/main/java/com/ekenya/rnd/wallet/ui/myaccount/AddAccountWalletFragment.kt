package com.ekenya.rnd.wallet.ui.myaccount

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.dialogs.dialog_confirm.ConfirmDialogCallBacks
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.MyAccountWalletModel
import com.ekenya.rnd.wallet.databinding.FragmentAddAccountWalletBinding
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.HashMap


class AddAccountWalletFragment :
    BaseWalletFragment<FragmentAddAccountWalletBinding>(FragmentAddAccountWalletBinding::inflate) {
    private var hashMap: HashMap<String, String> = HashMap()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = getString(R.string.add_account_wallet),
                subTitle = getString(R.string.add_account_wallet),
                cardTitle = getString(R.string.add_account_wallet),
                cardContent = getString(R.string.cardContent_wallet),
                hashMap = hashMap
            )
        )
        setUpUi()


    }

    private fun setUpUi() {
        binding.clToolBar.toolbar.setBackButton(
            (R.string.add_account_wallet),
            requireActivity()
        )

        binding.actAccountType.setUpSpinner(
            R.array.accountTypeWallet,
            onItemClick = { parent, view, pos, id ->
                val selectedItem = parent?.adapter?.getItem(pos).toString()
                Timber.e("SPINNER %s", selectedItem)
            })
        binding.btnContinue.setOnClickListener {
            if (addValidations()) {

                hashMap["Transfer From"] = binding.actAccountType.text.toString()

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        getString(R.string.add_account_wallet),
                        getString(R.string.add_account_wallet),
                        hashMap,
                        dialogCallback
                    )
                }
            }

        }
    }

    private fun addValidations(): Boolean {
        if (binding.actAccountType.text.isNullOrEmpty() || binding.actAccountType.text.isNullOrBlank()) {
            binding.actAccountType.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }

    companion object {
        fun newInstance() =
            AddAccountWalletFragment()
    }

}