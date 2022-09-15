package com.ekenya.rnd.wallet.ui.home.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekenya.rnd.common.auth.utils.toast
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentMainDepositWalletBinding
import com.ekenya.rnd.wallet.databinding.FragmentMakeDepositWalletBinding
import com.ekenya.rnd.wallet.utils.BaseWalletFragment
import com.ekenya.rnd.wallet.utils.timber


class MainDepositWalletFragment :
    BaseWalletFragment<FragmentMainDepositWalletBinding>
        (FragmentMainDepositWalletBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timber("Main Deposit")
    }
}