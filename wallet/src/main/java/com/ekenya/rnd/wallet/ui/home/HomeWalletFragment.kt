package com.ekenya.rnd.wallet.ui.home

import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import javax.inject.Inject
import androidx.lifecycle.ViewModelProvider
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.WalletMenuModel
import com.ekenya.rnd.wallet.databinding.FragmentHomeWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.OnWalletMenuItemClickedListener
import com.ekenya.rnd.wallet.ui.adapters.WalletAdapter
import com.ekenya.rnd.wallet.utils.setBackButton

class HomeWalletFragment : BaseDaggerFragment(), OnWalletMenuItemClickedListener {

    private lateinit var walletAdapter: WalletAdapter
    private lateinit var binding: FragmentHomeWalletBinding

    companion object {
        fun newInstance() = HomeWalletFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeWalletBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        binding
            .clToolBar
            .toolbar
            .setBackButton(
                R.string.home_wallet,
                requireActivity()
            )


    }

    private fun setUpUi() {
        walletAdapter = WalletAdapter(WalletMenuModel.getWalletMenuModelModel(), this)
        binding.rvMenus.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = walletAdapter
        }
    }

    override fun onWalletMenuClicked(view: View, model: WalletMenuModel) {
        when (view.id) {
            R.id.cvWalletMenu -> {
                when (model.menuId) {
                    1 -> {
                        findNavController().navigate(R.id.mainMyAccountWalletFragment)
                    }
                    2 -> {
                        findNavController().navigate(R.id.mainFundsTransferFragmentWallet)
                    }
                    3 -> {
                        findNavController().navigate(R.id.mainBillWalletFragment)
                    }
                    4 -> {
                        findNavController().navigate(R.id.mainBuyAirTimeWalletFragment)
                    }
                    5 -> {
                        findNavController().navigate(R.id.mainDepositWalletFragment)
                    }
                    6 -> {
                        findNavController().navigate(R.id.mainWithdrawWalletFragment)
                    }
                }
            }

        }

    }


}