package com.ekenya.rnd.merchant.ui.fragments.successful_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.common.dialogs.base.adapter_detail.DetailDialogAdapter
import com.ekenya.rnd.merchant.R
import com.ekenya.rnd.common.dialogs.base.adapter_detail.model.DialogDetailCommon
import com.ekenya.rnd.merchant.databinding.FragmentSucessfulMerchantBinding
import dagger.android.support.DaggerFragment

class SuccessfulFragmentMerchant : DaggerFragment() {

    private lateinit var binding: FragmentSucessfulMerchantBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSucessfulMerchantBinding.inflate(inflater, container, false).also {
            binding = it
        }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    // set up UI
    private fun setUpUI() {

        binding.recyclerViewDetails.apply {
            // create adapter
            val dialogAdapter = DetailDialogAdapter()

            // create a layout manager
            val linearLayoutManager = LinearLayoutManager(context)

            //set the layout manager
            layoutManager = linearLayoutManager

            // submit a list
            dialogAdapter.submitList(getDummyDetails)

            //set the adapter
            adapter = dialogAdapter
        }

        // navigate to home page
        binding.buttonContinue.setOnClickListener {
            findNavController().navigate(R.id.action_successful_to_home)
        }

//        binding.text

    }

    //dummy details
    private val getDummyDetails = mutableListOf<DialogDetailCommon>().apply {
        add(DialogDetailCommon("AMOUNT:","Kes 2000.00"))
        add(DialogDetailCommon("NAME:","Alex Mwangi"))
        add(DialogDetailCommon("MERCHANT NUMBER:","123456"))
        add(DialogDetailCommon("SPLIT BILL:","NO"))
        add(DialogDetailCommon("CHARGES","Kes 00.00"))
        add(DialogDetailCommon("TIME & DATE:","12-04-2021 | 4:00PM"))
        add(DialogDetailCommon("REF ID:","1234567890"))
    }

}
