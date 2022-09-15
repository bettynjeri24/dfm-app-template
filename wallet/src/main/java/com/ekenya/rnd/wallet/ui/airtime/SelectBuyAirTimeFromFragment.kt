package com.ekenya.rnd.wallet.ui.airtime

import android.os.Bundle
import android.provider.SimPhonebookContract.SimRecords.PHONE_NUMBER
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.ServiceProviderWalletModel
import com.ekenya.rnd.wallet.databinding.FragmentSelectBuyAirTimeFromWalletBinding
import com.ekenya.rnd.wallet.databinding.FragmentZukuInternetWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.CustomArrayAdapter
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap


class SelectBuyAirTimeFromFragment :
    BaseWalletBottomSheetDialogFragment<FragmentSelectBuyAirTimeFromWalletBinding>(
        FragmentSelectBuyAirTimeFromWalletBinding::inflate
    ) {
    private var hashMap: HashMap<String, String> = HashMap()


    //init select contact adapter
    private val customArrayAdapter: CustomArrayAdapter by lazy {
        CustomArrayAdapter(
            requireContext(),
            ServiceProviderWalletModel.getAllServiceProviderModel()
        )
    }

    override fun onResume() {
        super.onResume()
        val budgetMonths = resources.getStringArray(R.array.my_account)
        val arrayAdapterMonths = ArrayAdapter(
            requireContext(), R.layout.text_layout_wallet, budgetMonths
        )
        binding.aCtSelectAccount.setAdapter(arrayAdapterMonths)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {

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
        setUpUI()
    }

    private fun setUpUI() {
        setTransparentBackground()
        binding.cvBankAccountBuyAirTime.setOnClickListener {
            binding.cvMobileMoneyBg.setBackgroundColor(resources.getColor(R.color.grey_tint_wallet))
            binding.cvBankAccountBg.setBackgroundColor(resources.getColor(R.color.light_blue_wallet))

            binding.clBankAccountBuyAirTime.isVisible = true
            binding.clMobileMoneyBuyAirTime.isVisible = false
        }
        binding.cvMobileMoneyBuyAirTime.setOnClickListener {
            binding.cvMobileMoneyBg.setBackgroundColor(resources.getColor(R.color.light_blue_wallet))
            binding.cvBankAccountBg.setBackgroundColor(resources.getColor(R.color.grey_tint_wallet))
            binding.clBankAccountBuyAirTime.isVisible = false
            binding.clMobileMoneyBuyAirTime.isVisible = true
        }


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
                    toasty("You Select Position: ${prodObj.titleServiceProvider} ")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    toasty("Nothing Selected")
                }
            }

        binding.btnBankAccount.setOnClickListener {
            if (addValidations()) {

                hashMap["CHARGES:"] = getString(R.string.kes_0_00_wallet)
                hashMap["BUY FROM:"] = binding.aCtSelectAccount.text.toString()
                hashMap["BUY FOR:"] = PHONENUMBER
                hashMap["AMOUNT:"] = AMOUNT

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        "${getString(R.string.title_buy_airtime_confirm_dialog)} ",
                        getString(
                            R.string.sub_title_buy_airtime_confirm_dialog,
                            binding.aCtSelectAccount.text.toString()
                        ),
                        hashMap,
                        dialogCallback
                    )
                }
            }

        }
        binding.btnMobileMoney.setOnClickListener {
            if (addValidationsMobileMoney()) {

                hashMap["CHARGES:"] = getString(R.string.kes_0_00_wallet)
                hashMap["BUY FROM:"] = PHONENUMBER
                hashMap["BUY FOR:"] =binding.edtPhoneNumber.text.toString()
                hashMap["AMOUNT:"] = AMOUNT

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        "${getString(R.string.title_buy_airtime_confirm_dialog)} ",
                        getString(
                            R.string.sub_title_buy_airtime_confirm_dialog,
                            binding.edtPhoneNumber.text.toString()
                        ),
                        hashMap,
                        dialogCallback
                    )
                }
            }

        }


    }

    private fun addValidations(): Boolean {
        if (binding.aCtSelectAccount.text.isNullOrEmpty() || binding.aCtSelectAccount.text.isNullOrBlank()) {
            binding.aCtSelectAccount.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }


    private fun addValidationsMobileMoney(): Boolean {
        if (binding.edtPhoneNumber.text.isNullOrEmpty() ||
            binding.edtPhoneNumber.text.isNullOrBlank()
        ) {
            binding.edtPhoneNumber.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }


}

