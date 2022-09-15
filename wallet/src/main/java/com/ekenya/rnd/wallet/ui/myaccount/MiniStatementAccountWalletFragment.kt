package com.ekenya.rnd.wallet.ui.myaccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.DepositHistoryWalletModel
import com.ekenya.rnd.wallet.data.MiniStatementWalletModel
import com.ekenya.rnd.wallet.databinding.FragmentMiniStatementAccountWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.DepositHistoryWalletAdapter
import com.ekenya.rnd.wallet.ui.adapters.MiniStatementAccountWalletAdapter
import com.ekenya.rnd.wallet.utils.BaseWalletFragment
import com.ekenya.rnd.wallet.utils.setBackButton

class MiniStatementAccountWalletFragment :
    BaseWalletFragment<FragmentMiniStatementAccountWalletBinding>(
        FragmentMiniStatementAccountWalletBinding::inflate
    ) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clToolBar.toolbar.setBackButton(
            (R.string.mini_statement),
            requireActivity()
        )

        binding.rvMiniStatement.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter =
                MiniStatementAccountWalletAdapter(MiniStatementWalletModel.getMiniStatementModel())
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