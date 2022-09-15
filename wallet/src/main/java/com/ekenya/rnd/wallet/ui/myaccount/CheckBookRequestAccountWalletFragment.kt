package com.ekenya.rnd.wallet.ui.myaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.dialogs.dialog_confirm.ConfirmDialogCallBacks
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentCheckRequestAccountWalletBinding
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap

class CheckBookRequestAccountWalletFragment :
    BaseWalletFragment<FragmentCheckRequestAccountWalletBinding>(
        FragmentCheckRequestAccountWalletBinding::inflate
    ) {
    private var hashMap: HashMap<String, String> = HashMap()

    override fun onResume() {
        super.onResume()
        val getStringArray = resources.getStringArray(R.array.check_book_size)
        val arrayAdapter = ArrayAdapter(
            requireContext(), R.layout.text_layout_wallet, getStringArray
        )
        binding.actChequePageSize.setAdapter(arrayAdapter)


        val getStringArrayCheque = resources.getStringArray(R.array.check_type_size)
        val arrayAdapterCheque = ArrayAdapter(
            requireContext(), R.layout.text_layout_wallet, getStringArrayCheque
        )
        binding.actStopChequeSelect.setAdapter(arrayAdapterCheque)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = getString(R.string.check_request_wallet),
                subTitle = getString(R.string.check_request_wallet),
                cardTitle = getString(R.string.check_request_wallet),
                cardContent = getString(R.string.cardContent_wallet),
                hashMap = hashMap
            )
        )

        setUpUi()
    }

    private fun setUpUi() {

        binding.clToolBar.toolbar.setBackButton(
            (R.string.check_request_wallet),
            requireActivity()
        )
        setOnCheckedChangeListener()
    }

    private fun setOnCheckedChangeListener() {
        binding.radioGroupChequeOption.setOnCheckedChangeListener { group, checkedId ->
            if (R.id.radioButtonNewCheque == checkedId) {
                binding.clNewCheque.isVisible = true
                binding.clStopCheque.isVisible = false

            } else if (R.id.radioButtonStopCheque == checkedId) {
                binding.clNewCheque.isVisible = false
                binding.clStopCheque.isVisible = true

            }
        }

        setUpBtnContinue()

    }

    private fun setUpBtnContinue() {
        binding.btnContinue.setOnClickListener {
            if (binding.radioButtonNewCheque.isChecked) {
                if (addValidationsNewCheque()) {

                    hashMap["Last Series:"] = binding.edtLastSeries.text.toString()
                    hashMap["Cheque Book Size:"] = binding.actChequePageSize.text.toString()
                    hashMap["CHARGES:"] = getString(R.string.kes_0_00_wallet)

                    lifecycleScope.launch {
                        showHideProgress(getString(R.string.sending_request_wallet))
                        delay(2000)
                        showHideProgress(null)
                        showConfirmationDialog(
                            "${getString(R.string.Confirm_New_Cheque_Request_wallet)} ",
                            getString(R.string.Please_confirm_you_want_to_make_a_request_for_Cheque_book_wallet),
                            hashMap,
                            dialogCallback
                        )
                    }
                }


            } else if (binding.radioButtonStopCheque.isChecked) {
                if (addValidationsStopCheque()) {
                    hashMap["Cheques:"] = binding.actStopChequeSelect.text.toString()
                    hashMap["From Number:"] = binding.edtFromNumber.text.toString()
                    hashMap["To Number:"] = binding.edtToNumber.text.toString()
                    hashMap["Cheque Number:"] = binding.edtChequeNumber.text.toString()

                    lifecycleScope.launch {
                        showHideProgress(getString(R.string.sending_request_wallet))
                        delay(2000)
                        showHideProgress(null)
                        showConfirmationDialog(
                            "${getString(R.string.Confirm_New_Cheque_Request_wallet)} ",
                            getString(R.string.Please_confirm_you_want_to_make_a_request_for_Cheque_book_wallet),
                            hashMap,
                            dialogCallback
                        )
                    }
                }
            }


        }
    }


    private fun addValidationsNewCheque(): Boolean {
        if (binding.edtLastSeries.text.isNullOrEmpty() || binding.edtLastSeries.text.isNullOrBlank()) {
            binding.edtLastSeries.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.actChequePageSize.text.isNullOrEmpty()
            || binding.actChequePageSize.text.isNullOrBlank()
        ) {
            binding.actChequePageSize.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }

    private fun addValidationsStopCheque(): Boolean {
        if (binding.edtFromNumber.text.isNullOrEmpty() || binding.edtFromNumber.text.isNullOrBlank()) {
            binding.edtFromNumber.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.edtChequeNumber.text.isNullOrEmpty()
            || binding.edtChequeNumber.text.isNullOrBlank()
        ) {
            binding.edtChequeNumber.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.edtToNumber.text.isNullOrEmpty()
            || binding.edtToNumber.text.isNullOrBlank()
        ) {
            binding.edtToNumber.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.actStopChequeSelect.text.isNullOrEmpty()
            || binding.actStopChequeSelect.text.isNullOrBlank()
        ) {
            binding.actStopChequeSelect.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }


    companion object {
        fun newInstance() =
            AddAccountWalletFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}