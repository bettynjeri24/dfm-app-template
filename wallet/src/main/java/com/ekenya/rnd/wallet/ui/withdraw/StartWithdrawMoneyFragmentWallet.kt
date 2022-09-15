package com.ekenya.rnd.wallet.ui.withdraw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentWithdrawMoneyWalletBinding
import com.ekenya.rnd.wallet.utils.BaseWalletFragment
import com.ekenya.rnd.wallet.utils.setBackButton


class StartWithdrawMoneyFragmentWallet :
    BaseWalletFragment<FragmentWithdrawMoneyWalletBinding>(FragmentWithdrawMoneyWalletBinding::inflate) {

    companion object {
        fun newInstance() =
            StartWithdrawMoneyFragmentWallet()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    private fun setUpUi() {
        binding.clToolBar.toolbar.setBackButton(R.string.withdraw_money, requireActivity())

        binding.cvWithdrawFromAgent.setOnClickListener {
            findNavController().navigate(R.id.withdrawFromAgentFragmentWallet)
        }

        binding.cvCardlessWithdrawal.setOnClickListener {
            findNavController().navigate(R.id.cardlessWithdrawalFragmentWallet)
        }
    }


}