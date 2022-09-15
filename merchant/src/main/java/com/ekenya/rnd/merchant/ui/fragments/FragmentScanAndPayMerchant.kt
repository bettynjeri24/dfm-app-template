package com.ekenya.rnd.merchant.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekenya.rnd.merchant.databinding.FragmentScanAndPayMerchantBinding
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import dagger.android.support.DaggerFragment


class FragmentScanAndPayMerchant : DaggerFragment(), DecoratedBarcodeView.TorchListener {

    private lateinit var binding: FragmentScanAndPayMerchantBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentScanAndPayMerchantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        private const val TAG = "FragmentScanAndPayMerchant"
    }

    override fun onTorchOn() {

    }

    override fun onTorchOff() {
        TODO("Not yet implemented")
    }
}