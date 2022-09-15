package com.ekenya.rnd.wallet.ui.home.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekenya.rnd.wallet.databinding.FragmentMainBillsWalletBinding

class MainBillWalletFragment : Fragment() {

    private lateinit var binding: FragmentMainBillsWalletBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBillsWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() =
            MainBillWalletFragment().apply {
            }
    }
}