package com.ekenya.rnd.merchant.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.merchant.R
import com.ekenya.rnd.merchant.databinding.FragmentHomeMerchantBinding
import com.ekenya.rnd.common.code_scanner.AnyOrientationCaptureActivity
import com.ekenya.rnd.merchant.utils.MerchantHomeOptionState
import com.ekenya.rnd.merchant.utils.MerchantListUtils
import com.google.zxing.client.android.Intents
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch

/**
 * This is the first page of this module
 */
class FragmentHomeMerchant : DaggerFragment() {

    private lateinit var binding: FragmentHomeMerchantBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeMerchantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()

    }

    private fun setUpUi() {
        setUpBindings()
    }

    private fun setUpBindings() {
        // set up bindings
        binding.apply {
            recyclerviewHomeOptions.apply {
                // create adapter
                val optionAdapter = AdapterMerchantChoices { onClick(it) }

                // set adapter
                adapter = optionAdapter

                //give it a list
                optionAdapter.submitList(MerchantListUtils.merchantPaymentOptions)

                // set layout manager
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        //Navigate back
        binding.apply {
            toolbar.setNavigationOnClickListener {
                requireActivity().finish()
            }
        }
    }

    private fun onClick(homeOption: MerchantHomeOptionState) {

        when (homeOption) {

            // navigate to direct payment
            MerchantHomeOptionState.DirectPayment -> {
                findNavController().navigate(R.id.action_home_to_directPayment)
            }

            //navigate to scan and pay fragment
            MerchantHomeOptionState.ScanAndPay -> {
                scanCustomScanner()
            }

            MerchantHomeOptionState.EventsAndTickets -> {
                findNavController().navigate(R.id.action_home_to_eventsAndTickets)
            }

            MerchantHomeOptionState.HotDeals -> {
                findNavController().navigate(R.id.action_home_to_hotdeals)
            }

        }
    }

    // method to launch scanner activity
    private fun scanCustomScanner() {
        val options = ScanOptions()
        options.setOrientationLocked(false)
        options.captureActivity = AnyOrientationCaptureActivity::class.java
        barcodeLauncher.launch(options)
    }

    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            val originalIntent = result.originalIntent
            if (originalIntent == null) {
                Log.d("MainActivity", "Cancelled scan")
                Toast.makeText(
                    requireContext(),
                    "Cancelled",
                    Toast.LENGTH_LONG
                ).show()
            } else if (originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                Log.d(
                    "MainActivity",
                    "Cancelled scan due to missing camera permission"
                )
                Toast.makeText(
                    requireContext(),
                    "Cancelled due to missing camera permission",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Log.d("MainActivity", "Scanned")
            Toast.makeText(
                requireContext(),
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()
        }
    }


}