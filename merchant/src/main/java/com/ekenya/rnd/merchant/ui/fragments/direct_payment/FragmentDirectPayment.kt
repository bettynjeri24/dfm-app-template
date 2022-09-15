package com.ekenya.rnd.merchant.ui.fragments.direct_payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.form_validation.ui_extensions.formatAsCurrency
import com.ekenya.rnd.common.form_validation.ui_extensions.removeNonDigits
import com.ekenya.rnd.merchant.R
import com.ekenya.rnd.merchant.databinding.FragmentDirectPaymentMerchantBinding
import com.ekenya.rnd.merchant.ui.fragments.direct_payment.bottom_sheet_merchant_payment.BottomSheetMerchantPayment
import com.ekenya.rnd.merchant.ui.fragments.select_contact.FragmentSelectContact
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentDirectPayment : BaseDaggerFragment(){

    private lateinit var binding: FragmentDirectPaymentMerchantBinding

    private var searched = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDirectPaymentMerchantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    override fun onResume() {
        super.onResume()
        binding.groupMerchantDetails.isVisible = searched
    }

    private fun setUpUi() {
        setUpBindings()
        if (FragmentSelectContact.contactHashSet.isNotEmpty()) {
            showMerchantPaymentBottomSheet()
        }
    }

    private fun setUpBindings() {
        binding.button.setOnClickListener {
            when (binding.groupMerchantDetails.isVisible) {
                // when that layout is visible, it means a search has already been done
                true -> {
                    //decide whether to just pay or split the bill
                    payOrSplitBill()
                }

                // simulate a search
                false -> {
                    simulateSearch()
                }
            }
        }

        //Navigate back
        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController(this@FragmentDirectPayment).navigateUp()
            }
            editTextTextAmount.formatAsCurrency()
        }

    }

    private fun simulateSearch() {
        lifecycleScope.launch {
           simulateSearching()
            binding.groupMerchantDetails.isVisible = true
            searched = true
            binding.button.text = "CONTINUE"
        }
    }

    private fun payOrSplitBill() {

        if (binding.editTextTextAmount.text.isNullOrEmpty()){
            binding.editTextTextAmount.error = "Please enter amount"
            return
        }else{
            amount = binding.editTextTextAmount.text.toString().removeNonDigits().toInt()
        }

        when (binding.switchSplitBill.isChecked) {
            true -> {
                findNavController(this).navigate(R.id.action_directPayment_to_splitBill)
            }
            false -> {
                showMerchantPaymentBottomSheet()
            }
        }
    }

    private suspend fun simulateSearching(){
            showHideProgress("Searching merchant")
            delay(2000)
            showHideProgress(null)
    }

    private fun showMerchantPaymentBottomSheet() {
        val bottomSheetDialogFragment: BottomSheetDialogFragment =
            BottomSheetMerchantPayment (toSuccess = {navigateToSuccess()}, toSelectContact = {navigateToSelectContact()})
        bottomSheetDialogFragment.show(
            requireActivity().supportFragmentManager,
            bottomSheetDialogFragment.tag
        )
    }

    private fun navigateToSuccess() {
        findNavController().navigate(R.id.action_directPayment_to_successfulFragment)
    }

    private fun navigateToSelectContact() {
        findNavController().navigate(R.id.action_directPayment_to_SelectContact)
    }

    companion object {
        var amount = 0
    }



}