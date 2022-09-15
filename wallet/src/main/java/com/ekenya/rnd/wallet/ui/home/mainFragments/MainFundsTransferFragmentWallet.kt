package com.ekenya.rnd.wallet.ui.home.mainFragments

import android.os.Bundle
import android.view.View
import com.ekenya.rnd.wallet.databinding.FragmentMainFundTransferWalletBinding
import com.ekenya.rnd.wallet.utils.BaseWalletFragment
import com.ekenya.rnd.wallet.utils.timber


class MainFundsTransferFragmentWallet :
    BaseWalletFragment<FragmentMainFundTransferWalletBinding>
        (FragmentMainFundTransferWalletBinding::inflate) {

    companion object {
        fun newInstance() = MainFundsTransferFragmentWallet()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timber("MainFundsTransferFragmentWallet")
    }


}