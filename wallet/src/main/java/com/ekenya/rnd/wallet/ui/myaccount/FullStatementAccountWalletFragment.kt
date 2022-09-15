package com.ekenya.rnd.wallet.ui.myaccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.DepositHistoryWalletModel
import com.ekenya.rnd.wallet.data.FullStatementWalletModel
import com.ekenya.rnd.wallet.databinding.FragmentFullStatementAccountWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.DepositHistoryWalletAdapter
import com.ekenya.rnd.wallet.ui.adapters.FullStatementAccountWalletAdapter
import com.ekenya.rnd.wallet.utils.BaseWalletFragment
import com.ekenya.rnd.wallet.utils.createSuccessBundle
import com.ekenya.rnd.wallet.utils.setBackButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap

class FullStatementAccountWalletFragment :
    BaseWalletFragment<FragmentFullStatementAccountWalletBinding>(
        FragmentFullStatementAccountWalletBinding::inflate
    ) {

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
        setUpUi()

    }

    private fun setUpUi() {
        binding.clToolBar.toolbar.setBackButton(
            (R.string.full_statement),
            requireActivity()
        )

        binding.rvFullStatement.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter =
                FullStatementAccountWalletAdapter(FullStatementWalletModel.getFullStatementModel())
        }

        binding.btnGenerateNewStatement.setOnClickListener {
            hashMap[getString(R.string.full_statement)] =
                getString(R.string.generate_new_statement_wallet)

            lifecycleScope.launch {
                showHideProgress(getString(R.string.sending_request_wallet))
                delay(2000)
                showHideProgress(null)
                showConfirmationDialog(
                    getString(R.string.generate_new_statement_wallet),
                    getString(R.string.generate_new_statement_wallet),
                    hashMap,
                    dialogCallback
                )


            }
        }

        binding.btnSearchForOlderStatements.setOnClickListener {
            hashMap[getString(R.string.full_statement)] =
                getString(R.string.search_for_older_statements)

            lifecycleScope.launch {
                showHideProgress(getString(R.string.sending_request_wallet))
                delay(2000)
                showHideProgress(null)
                showConfirmationDialog(
                    getString(R.string.search_for_older_statements),
                    getString(R.string.search_for_older_statements),
                    hashMap,
                    dialogCallback
                )


            }
        }
    }

    companion object {
        fun newInstance() =
            AddAccountWalletFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}