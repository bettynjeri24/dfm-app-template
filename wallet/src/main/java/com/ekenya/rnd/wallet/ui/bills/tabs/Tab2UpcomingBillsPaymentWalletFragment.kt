package com.ekenya.rnd.wallet.ui.bills.tabs

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.BillsWalletModel
import com.ekenya.rnd.wallet.databinding.FragmentAllBillPaymentWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.listeners.OnBillItemClickedListener
import com.ekenya.rnd.wallet.ui.adapters.UpComingBillWalletAdapter
import com.ekenya.rnd.wallet.utils.BILL_PAYMENT_IMAGE
import com.ekenya.rnd.wallet.utils.BaseWalletFragment
import com.ekenya.rnd.wallet.utils.SUCCESSFUL_TITLE
import com.ekenya.rnd.wallet.utils.setBackButton

class Tab2UpcomingBillsPaymentWalletFragment : BaseWalletFragment<FragmentAllBillPaymentWalletBinding>(FragmentAllBillPaymentWalletBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clToolBar.toolbar.setBackButton(R.string.bills_title_wallet, requireActivity())

        binding.tvAllBills.isVisible = false
        binding.rvAllBills.isVisible = false
        binding.rvFrequentBills.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = UpComingBillWalletAdapter(
                BillsWalletModel.getFrequentBillsModel(),
                onBillItemClickedListener
            )
        }
    }

    companion object {
        fun newInstance() =
            Tab2UpcomingBillsPaymentWalletFragment()
    }

    val onBillItemClickedListener = object : OnBillItemClickedListener {
        override fun onBillsWalletModelClicked(view: View, model: BillsWalletModel) {
            when (view.id) {
                R.id.mcVRoout -> {
                    /*if (model.titleBills == getString(R.string.zuku_internet_wallet)) {
                        findNavController().navigate(R.id.zukuInternetFragmentWallet)
                    } else if (
                        model.titleBills == getString(R.string.kplc_prepaid_title_wallet)
                    ) {
                        findNavController().navigate(R.id.kplcPrepaidFragmentWallet)
                    } else {*/
                    BILL_PAYMENT_IMAGE = model.imageBills
                    SUCCESSFUL_TITLE = model.titleBills
                    val bundle = Bundle()
                    bundle.putParcelable("BillsWalletModel", model)
                    findNavController().navigate(R.id.generalBillPaymentFragmentWallet,bundle)
                    //}

                }
            }
            /*when (view.id) {
                R.id.mcVRoout -> {
                    if (model.titleBills == getString(R.string.zuku_internet_wallet)) {
                        findNavController().navigate(R.id.zukuInternetFragmentWallet)
                    } else if (
                        model.titleBills == getString(R.string.kplc_prepaid_title_wallet)
                    ) {
                        findNavController().navigate(R.id.kplcPrepaidFragmentWallet)
                    }
                }
                R.id.btnBillsPaid -> {
                    if (model.titleBills == getString(R.string.zuku_internet_wallet)) {
                        findNavController().navigate(R.id.zukuInternetFragmentWallet)
                    } else if (
                        model.titleBills == getString(R.string.kplc_prepaid_title_wallet)
                    ) {
                        findNavController().navigate(R.id.kplcPrepaidFragmentWallet)
                    }
                }
            }*/
        }
    }
}