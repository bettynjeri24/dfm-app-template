package com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.limits

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.ekenya.rnd.common.abstractions.BaseBottomSheetDialogFragment
import com.ekenya.rnd.mycards.databinding.BottomSheetLimitsCardsBinding
import com.ekenya.rnd.mycards.ui.dialog.alertDialog.showAlertDialog
import com.ekenya.rnd.common.dialogs.base.adapter_detail.model.DialogDetailCommon
import com.ekenya.rnd.mycards.ui.fragments.home.MyCardsHomeFragment


class BottomSheetLimits(private val navigateToAuth: () -> Unit) : BaseBottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetLimitsCardsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetLimitsCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpUi()
    }

    private fun setUpUi() {

        TransitionManager.beginDelayedTransition(binding.root);

        binding.apply {

            //change visibility of atm group
            switchAtm.setOnCheckedChangeListener { _, isChecked ->
                groupAtm.isVisible = isChecked
            }

            //change visibility of online group
            switchOnline.setOnCheckedChangeListener { _, isChecked ->

                groupOnline.isVisible = isChecked
            }

            //change visibility of POS group
            switchPos.setOnCheckedChangeListener { _, isChecked ->
                groupPos.isVisible = isChecked
            }

            binding.button.setOnClickListener {
                showDialog()
            }

        }
    }

    private fun showDialog() {

        val merchantPaymentDialog = showAlertDialog {

            // not cancellable
            cancelable = false

            // set title
            setDialogTitle("Confirm Card Limit")

            // set sub title
            setDialogSubtitle("Please confirm you want to set a card limit for your Gold credit 5535•••• ••••22978")

            // add dialog details
            setUpRecyclerAdapter(details)

            // add execution logic to confirm button click
            confirmButtonClickListener {

                navigateToAuth.invoke()

                MyCardsHomeFragment.details = details.apply {
                    add(DialogDetailCommon("TIME & DATE:", "12-04-2021 | 4:00PM"))
                }
                this@BottomSheetLimits.dismiss()

            }

            cancelButtonClickListener {

            }


        }

        //  show the dialog
        merchantPaymentDialog.show()
    }

    private val details = mutableListOf<DialogDetailCommon>().apply {
        add(DialogDetailCommon("ATM WITHDRAWAL DAILY LIMIT:", "Ksh 1,000.00"))
        add(DialogDetailCommon("No. Of TRANSACTION:", ""))
        add(DialogDetailCommon("AMOUNT FOR EACH:", "Kes 10.00"))
        add(DialogDetailCommon("EMERGENCY CONTACT::", "Kes 10.00"))

    }


}