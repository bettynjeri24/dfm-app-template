package com.ekenya.rnd.wallet.ui.withdraw.w2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentWithdrawWalletBinding
import com.ekenya.rnd.wallet.utils.setBackButton


class WithdrawFragmentWallet : Fragment() {
    private lateinit var binding: FragmentWithdrawWalletBinding

    companion object {
        fun newInstance() =
            WithdrawFragmentWallet()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWithdrawWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clToolBar.toolbar.setBackButton(R.string.withdraw, requireActivity())
    }


}