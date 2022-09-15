package com.ekenya.rnd.wallet.ui.airtime

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.ServiceProviderWalletModel
import com.ekenya.rnd.wallet.databinding.FragmentBuyAirtimeWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.CustomArrayAdapter
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class BuyAirTimeWalletFragment :
    BaseWalletFragment<FragmentBuyAirtimeWalletBinding>(FragmentBuyAirtimeWalletBinding::inflate) {

    private var hashMap: HashMap<String, String> = HashMap()

    //init select contact adapter
    private val customArrayAdapter: CustomArrayAdapter by lazy {
        CustomArrayAdapter(
            requireContext(),
            ServiceProviderWalletModel.getAllServiceProviderModel()
        )
    }
    private var phoneNumber: String = ""
    override fun onResume() {
        super.onResume()
        val budgetMonths = resources.getStringArray(R.array.my_account)
        val arrayAdapterMonths = ArrayAdapter(
            requireContext(), R.layout.text_layout_wallet, budgetMonths
        )
        binding.aCtSelectAccount.setAdapter(arrayAdapterMonths)

        binding.actSelectProvider.setAdapter(
            customArrayAdapter
        )


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            phoneNumber = bundle.getString(getString(R.string.phone_number_wallet), "")
        }

        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = getString(R.string.buy_airtime_wallet),
                subTitle = getString(R.string.buy_airtime_wallet),
                cardTitle = getString(R.string.phone_number_wallet),
                cardContent = getString(R.string.cardContent_wallet),
                hashMap = hashMap

            )
        )

        binding.clToolBar.toolbar.setBackButton(R.string.buy_airtime_wallet, requireActivity())

        binding.spinnerServiceProvider.adapter = customArrayAdapter
        binding.spinnerServiceProvider.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val prodObj: ServiceProviderWalletModel =
                        customArrayAdapter.getItem(position) as ServiceProviderWalletModel
                    SELECT_SERVICE_PROVIDER = prodObj.titleServiceProvider
                    toasty("You Select Position: ${prodObj.titleServiceProvider} ")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    toasty("Nothing Selected")
                }
            }


        if (phoneNumber.isNotEmpty()) {
            binding.edtPhoneNumber.setText(phoneNumber)
            binding.edtPhoneNumber.isFocusable = false
        } else {
            binding.edtPhoneNumber.setText("")
            binding.edtPhoneNumber.isFocusable = true
        }

        binding.btnContinue.setOnClickListener {

            if (addValidations()) {

                hashMap["CHARGES:"] = getString(R.string.kes_0_00_wallet)
                hashMap["AMOUNT:"] = binding.edtAmount.text.toString()
                //hashMap["BUY FROM:"] = binding.aCtSelectAccount.text.toString()

//                val bundle = Bundle()
//                bundle.putString(
//                    getString(R.string.AMOUNT),
                AMOUNT = binding.edtAmount.text.toString()
//                )
//                bundle.putString(
//                    getString(R.string.PHONE_NUMBER),
                PHONENUMBER = binding.edtPhoneNumber.text.toString()
//                )
                findNavController().navigate(R.id.selectBuyAirTimeFromFragment)
//                 lifecycleScope.launch {
//                     showHideProgress(getString(R.string.sending_request_wallet))
//                     delay(2000)
//                     showHideProgress(null)
//                     showConfirmationDialog(
//                         "${getString(R.string.title_buy_airtime_confirm_dialog)} ",
//                         getString(
//                             R.string.sub_title_buy_airtime_confirm_dialog,
//                             binding.edtPhoneNumber.text.toString()
//                         ),
//                         hashMap,
//                         dialogCallback
//                     )
//                 }
            }

        }

        binding.textInputPhone.setEndIconOnClickListener {
            fetchPhoneNo()
        }


    }

    private fun addValidations(): Boolean {
        if (binding.edtAmount.text.isNullOrEmpty() || binding.edtAmount.text.isNullOrBlank()) {
            binding.edtAmount.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.edtPhoneNumber.text.isNullOrEmpty()
            || binding.edtPhoneNumber.text.isNullOrBlank()
        ) {
            binding.edtPhoneNumber.error = getString(R.string.enter_all_field_wallet)
            return false
        }
//        if (binding.aCtSelectAccount.text.isNullOrEmpty() || binding.aCtSelectAccount.text.isNullOrBlank()) {
//            binding.aCtSelectAccount.error = getString(R.string.enter_all_field_wallet)
//            return false
//        }
        else {
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