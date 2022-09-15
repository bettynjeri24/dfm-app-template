package com.ekenya.rnd.wallet.ui.bills.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.BillsWalletModel
import com.ekenya.rnd.wallet.databinding.FragmentAllBillPaymentWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.AllBillWalletAdapter
import com.ekenya.rnd.wallet.ui.adapters.listeners.OnBillItemClickedListener
import com.ekenya.rnd.wallet.utils.BILL_PAYMENT_IMAGE
import com.ekenya.rnd.wallet.utils.SUCCESSFUL_TITLE
import com.ekenya.rnd.wallet.utils.setBackButton

class Tab1AllBillPaymentWalletFragment : Fragment(), OnBillItemClickedListener {

    private lateinit var binding: FragmentAllBillPaymentWalletBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBillPaymentWalletBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clToolBar.toolbar.setBackButton(R.string.bills_title_wallet, requireActivity())

        binding.rvAllBills.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = AllBillWalletAdapter(
                BillsWalletModel.getAllBillsModel(),
                this@Tab1AllBillPaymentWalletFragment
            )
        }

        binding.rvFrequentBills.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = AllBillWalletAdapter(
                BillsWalletModel.getFrequentBillsModel(),
                this@Tab1AllBillPaymentWalletFragment
            )
        }


    }

    companion object {
        fun newInstance() =
            Tab1AllBillPaymentWalletFragment()
    }

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
    }
}