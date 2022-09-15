package com.ekenya.rnd.wallet.ui.fundtransfer.pesalink.tophone

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentPhoneDialogWalletBinding
import com.ekenya.rnd.wallet.utils.setBackButton


class PhoneDialogFragmentWallet() : DialogFragment() {
    private lateinit var binding: FragmentPhoneDialogWalletBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = FragmentPhoneDialogWalletBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.92).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isCancelable = false

        binding.toolbar.setBackButton(
            title = R.string.pesa_link_to_phone_wallet,
            context = requireActivity(),
            setNavIcon = R.drawable.ic_cancel
        ) {
            dismiss()
        }

        binding.btnContinue.setOnClickListener {
            dismiss()
        }
    }


}