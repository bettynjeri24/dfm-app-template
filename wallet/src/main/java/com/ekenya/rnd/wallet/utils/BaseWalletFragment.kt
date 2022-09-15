package com.ekenya.rnd.wallet.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.auth.AuthResult
import com.ekenya.rnd.common.dialogs.dialog_confirm.ConfirmDialogCallBacks
import com.ekenya.rnd.wallet.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.HashMap

abstract class BaseWalletFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : BaseDaggerFragment() {

    private var _binding: VB? = null
    val binding get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null) throw IllegalArgumentException("Binding Not Found")

        /*setFragmentResultListener("requestKey") { _, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result: AuthResult = bundle.get("authResult") as AuthResult
            when (result) {
                AuthResult.AUTH_SUCCESS -> {
                    findNavController().navigate(
                        R.id.successfulFragmentWallet,
                        bundle
                    )
                }
                AuthResult.AUTH_ERROR -> {
                    //handle error
                    findNavController().navigate(
                        R.id.successfulFragmentWallet
                    )
                }
            }

        }*/

        return binding.root
    }

    val dialogCallback = object : ConfirmDialogCallBacks {
        override fun confirm() {
            lifecycleScope.launch {
                showHideProgress(getString(R.string.sending_request_wallet))
                delay(2000)
                showHideProgress(null)
                findNavController().navigate(R.id.commonAuthFragment)
            }
        }

        override fun cancel() {
            timber("cancel")
        }
    }

    fun fragmentResultListener(createSuccessBundle: Bundle) {
        setFragmentResultListener("requestKey") { _, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result: AuthResult = bundle.get("authResult") as AuthResult

            when (result) {
                AuthResult.AUTH_SUCCESS -> {
                    findNavController().navigate(
                        R.id.successfulFragmentWallet,
                        createSuccessBundle
                    )
                }
                AuthResult.AUTH_ERROR -> {
                    // handle error
                }
            }
        }
    }

    var hashMapBaseWallet: HashMap<String, String> = HashMap()
}
