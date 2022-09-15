package com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentMobileMobeyFtWalletBinding
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap

class MobileMoneyFundTransferWalletFragment :
    BaseWalletFragment<FragmentMobileMobeyFtWalletBinding>(FragmentMobileMobeyFtWalletBinding::inflate) {

    private var hashMap: HashMap<String, String> = HashMap()
    private var phoneNumber: String = ""

    override fun onResume() {
        super.onResume()
        val budgetMonths = resources.getStringArray(R.array.account_type_wallet)
        val arrayAdapterMonths = ArrayAdapter(
            requireContext(),
            R.layout.text_layout_wallet,
            budgetMonths
        )
        binding.aCtSelectAccount.setAdapter(arrayAdapterMonths)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clToolBar.toolbar.setBackButton(
            (R.string.send_to_mpesa),
            requireActivity()
        )
        setUi()

        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = SUCCESSFUL_TITLE,
                subTitle = SUCCESSFUL_SUBTITLE,
                cardTitle = SUCCESSFUL_CARDTITLE,
                cardContent = SUCCESSFUL_CARDCONTENT,
                hashMap = hashMap

            )
        )
    }

    private fun setUi() {
        val bundle = this.arguments
        if (bundle != null) {
            phoneNumber = bundle.getString(getString(R.string.phone_number_wallet), "")
        }

        binding.btnContinue.setOnClickListener {
            if (addValidations()) {
                hashMap["Phone Number:"] = " ${binding.edtPhoneNumber.text}"
                hashMap["Amount:"] = "Kes ${binding.edtAmount.text}"
                hashMap["Transfer From:"] = binding.aCtSelectAccount.text.toString()

                SUCCESSFUL_CARDTITLE = getString(R.string.transfer_from_wallet)
                SUCCESSFUL_CARDCONTENT = binding.aCtSelectAccount.text.toString()

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        getString(R.string.Confirm_Send_to_Mpesa),
                        getString(
                            R.string.Please_confirm_you_are_making_an_Send_to_Mpesa_to,
                            binding.edtPhoneNumber.text.toString()
                        ),
                        hashMap,
                        dialogCallback
                    )
                }
            }
        }

        binding.edtPhoneNumber.setText(phoneNumber)
        setOnCheckedChangeListener()

        binding.tILayoutPhoneNumber.setEndIconOnClickListener {
            binding.radioButtonOthers.isChecked = true
            fetchPhoneNo()
        }
    }

    private fun setOnCheckedChangeListener() {
        binding.radioGroupSelfOrOthers.setOnCheckedChangeListener { group, checkedId ->
            if (R.id.radioButtonSelf == checkedId) {
                binding.edtPhoneNumber.setText(phoneNumber)
                binding.edtPhoneNumber.isFocusable = false
                if (phoneNumber.isEmpty()) {
                    binding.edtPhoneNumber.setText("")
                    binding.edtPhoneNumber.isFocusable = true
                    binding.radioButtonOthers.isChecked
                    !binding.radioButtonSelf.isChecked
                }
            } else if (R.id.radioButtonOthers == checkedId) {
                binding.edtPhoneNumber.setText("")
                binding.edtPhoneNumber.isFocusable = true
            }
        }

        if (binding.radioButtonSelf.isChecked) {
            binding.edtPhoneNumber.setText(phoneNumber)
            binding.edtPhoneNumber.isFocusable = false
        } else if (binding.radioButtonOthers.isChecked) {
            binding.edtPhoneNumber.setText("")
            binding.edtPhoneNumber.isFocusable = true
        }
    }

    private fun addValidations(): Boolean {
        if (binding.edtAmount.text.isNullOrEmpty() || binding.edtAmount.text.isNullOrBlank()) {
            binding.edtAmount.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.edtPhoneNumber.text.isNullOrEmpty() || binding.edtPhoneNumber.text.isNullOrBlank()) {
            binding.edtPhoneNumber.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.aCtSelectAccount.text.isNullOrEmpty() || binding.aCtSelectAccount.text.isNullOrBlank()) {
            binding.aCtSelectAccount.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }

    private fun fetchPhoneNo() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        resultLauncher.launch(intent)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                timber(contactDataList(data))
                binding.edtPhoneNumber.setText(contactDataList(data))
            }
        }
}
