package com.ekenya.rnd.wallet.ui.deposit.cheque

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.wallet.data.DepositHistoryWalletModel
import com.ekenya.rnd.wallet.databinding.FragmentTab2HistoryDepositChequeWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.DepositHistoryWalletAdapter

class Tab2HistoryChequeDepositWalletFragment : Fragment() {

    private lateinit var binding: FragmentTab2HistoryDepositChequeWalletBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTab2HistoryDepositChequeWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDepositHistory.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter =
                DepositHistoryWalletAdapter(DepositHistoryWalletModel.getDepositHistoryModel())
        }
    }


    companion object {
        fun newInstance() =
            Tab2HistoryChequeDepositWalletFragment()
    }
}