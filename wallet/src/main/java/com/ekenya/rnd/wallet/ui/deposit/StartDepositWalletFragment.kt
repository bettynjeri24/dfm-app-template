package com.ekenya.rnd.wallet.ui.deposit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentStartDepositWalletBinding
import com.ekenya.rnd.wallet.utils.setBackButton

class StartDepositWalletFragment : Fragment() {

    private lateinit var binding: FragmentStartDepositWalletBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartDepositWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clToolBar.toolbar.setBackButton(R.string.deposit_wallet, requireActivity())

        binding.cvChequeDeposit.setOnClickListener {
            findNavController().navigate(R.id.startChequeDepositWalletFragment)
        }

        binding.cvMpesaToAccount.setOnClickListener {
            findNavController().navigate(R.id.depositMpesaToAccountFragment)
        }
    }

    companion object {
        fun newInstance() =
            StartDepositWalletFragment()
    }
}