package com.ekenya.rnd.merchant.ui.fragments.auth

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.auth.biometrics.BiometricCallback
import com.ekenya.rnd.common.auth.lockscreen.LockScreenAuthenticator
import com.ekenya.rnd.common.auth.lockscreen.LockScreenCallback
import com.ekenya.rnd.common.auth.utils.AuthenticationWrapper
import com.ekenya.rnd.common.auth.utils.toast
import com.ekenya.rnd.merchant.R
import com.ekenya.rnd.merchant.databinding.FragmentAuthMerchantBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executor


class AuthFragmentMerchant : BaseDaggerFragment() {

    private val authenticationWrapper by lazy {

        AuthenticationWrapper(requireActivity(), biometricCallback, lockScreenCallback)

    }

    private lateinit var binding: FragmentAuthMerchantBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private var pinAdapter: PinAdapterMerchant = PinAdapterMerchant()

    // observe this list to get the pin entered
    private var pinList = MutableLiveData(mutableListOf<Pin>().apply {

        // initialize it with 4 dummy digits
        repeat(4) {
            add(Pin("*"))
        }

    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAuthMerchantBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setUpObserver()
    }


    private fun setUpUI() {
        binding.apply {

            // set up pin recyclerview
            recyclerView2.apply {
                pinAdapter.submitList(pinList.value)
                adapter = pinAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL, false
                )
            }


            // set up keyboard
            includeKeyBoard.apply {

                btnOne.getKeyboardDigit()

                btnTwo.getKeyboardDigit()

                btnThree.getKeyboardDigit()

                btnFour.getKeyboardDigit()

                btnFive.getKeyboardDigit()

                btnSix.getKeyboardDigit()

                btnSeven.getKeyboardDigit()

                btnEight.getKeyboardDigit()

                btnNine.getKeyboardDigit()

                btnZero.getKeyboardDigit()

                textViewFingerPrint.setBiometricsLauncher(BiometricManager.Authenticators.BIOMETRIC_STRONG)

                textViewFaceId.setBiometricsLauncher(BiometricManager.Authenticators.BIOMETRIC_WEAK)


                // removing the last digit
                btnErase.setOnClickListener {
                    pinList.value?.asReversed()?.forEach { pin ->
                        if (pin.digit != "*") {
                            pin.digit = "*"
                            pinList.value = pinList.value
                            return@setOnClickListener
                        }
                    }

                }
            }


        }

        setBiometricAuthentication()
    }


    //set up observer for the pin list
    private fun setUpObserver() {
        pinList.observe(viewLifecycleOwner) { pin ->
            pinAdapter.submitList(pin)
            pinAdapter.notifyDataSetChanged()

            // if pin has 4 digits, progress
            if (pin.filter { it.digit == "*" }.toList().isEmpty()) {
                lifecycleScope.launch {
                    simulateSearching()
                    findNavController().navigate(R.id.action_authFragment_to_successfulFragment)
                }
            }
        }
    }



    private fun setBiometricAuthentication() {
        executor = ContextCompat.getMainExecutor(requireContext())
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    // simulate loading
                    lifecycleScope.launch {
                        simulateSearching()
                        findNavController().navigate(R.id.action_authFragment_to_successfulFragment)
                    }
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(requireContext(), "Authentication Failed", Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Authentication")
            .setSubtitle("Authenticate to proceed")
            .setDeviceCredentialAllowed(true)
            .build()
    }



    private fun isBiometricReady(): Boolean {
        val biometricManager = BiometricManager.from(requireContext())
        return biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS
    }

    // get value from pressed button
    private fun Button.getKeyboardDigit() {

        setOnClickListener {

            //loop until you find a star, change it, update livedata, exit.
            pinList.value?.forEach { digit ->
                if (digit.digit == "*") {
                    digit.digit = text.toString()
                    pinList.value = pinList.value
                    return@setOnClickListener
                }
            }

        }
    }

    //launch biometrics
    private fun TextView.setBiometricsLauncher(biometricAllowedAuth: Int) {
        setOnClickListener {
            if (isBiometricReady()) {
                authenticationWrapper.biometricAuthenticator.authenticate(biometricAllowedAuth)
            }
        }
    }

    companion object {
        private const val TAG = "PinBottomSheet"
    }

    private val biometricCallback = object : BiometricCallback {
        /**
         * This three callbacks are for biometric authentication
         */
        //Error occured during authentication
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {

            /**
             * This when expression handles all the error codes if an authentication error occurs
             * @see {@https://developer.android.com/reference/android/hardware/biometrics/BiometricPrompt}
             */

            toast(errString.toString())

            when (errorCode) {

                BiometricPrompt.AUTHENTICATION_RESULT_TYPE_BIOMETRIC -> {
                }

                BiometricPrompt.AUTHENTICATION_RESULT_TYPE_DEVICE_CREDENTIAL -> {
                }

                //..... etc Customize this based on your use cases

            }
        }

        //Authentication was successful
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {

            simulateLoading()

        }

        //Authentication failed
        override fun onAuthenticationFailed() {
            toast("Authentication Failed")
        }

    }

    private val lockScreenCallback = object : LockScreenCallback {
        /**
         * This callback methods is invoked  when login screen with PIN is needed
         */

        override fun showAuthenticationScreen() {

            // Create the Confirm Credentials screen. You can customize the title and description. Or
            // we will provide a generic one for you if you leave it null

            val mKeyguardManager: KeyguardManager =
                requireActivity().getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

            val intent: Intent = mKeyguardManager.createConfirmDeviceCredentialIntent(null, null)

            startActivityForResult(
                intent,
                LockScreenAuthenticator.REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS
            )

        }
    }

    private fun simulateLoading() {
        lifecycleScope.launch {
            simulateSearching()
            findNavController().navigate(R.id.action_authFragment_to_successfulFragment)
        }
    }

    private suspend fun simulateSearching() {
        showHideProgress("Authenticating")
        delay(2000)
        showHideProgress(null)
    }

}