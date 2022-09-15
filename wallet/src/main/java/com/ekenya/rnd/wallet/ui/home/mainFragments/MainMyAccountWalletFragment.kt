package com.ekenya.rnd.wallet.ui.home.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekenya.rnd.wallet.databinding.FragmentMainMyAccountWalletBinding

class MainMyAccountWalletFragment : Fragment() {

    private lateinit var binding: FragmentMainMyAccountWalletBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMyAccountWalletBinding.inflate(inflater, container, false)
        return binding.root
    }


}