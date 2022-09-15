




package com.ekenya.rnd.wallet.ui.myaccount.tabs.unlink

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.MyAccountWalletModel
import com.ekenya.rnd.wallet.databinding.FragmentTab2UnLinkedMyAccountWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.MyAccountsWalletAdapter
import com.ekenya.rnd.wallet.ui.adapters.listeners.OnAccountsItemClickedListener
import com.ekenya.rnd.wallet.utils.BaseWalletFragment


class Tab2UnLinkedMyAccountFragment : BaseWalletFragment<FragmentTab2UnLinkedMyAccountWalletBinding>(
    FragmentTab2UnLinkedMyAccountWalletBinding::inflate
) , OnAccountsItemClickedListener {

    private lateinit var myAccountsWalletAdapter: MyAccountsWalletAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAccountsWalletAdapter =
            MyAccountsWalletAdapter(MyAccountWalletModel.getMyUnLinkedAccountWalletModel(), this)

        binding.rvAllMyAccounts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myAccountsWalletAdapter
        }


    }



    override fun onClickedItem(view: View, model: MyAccountWalletModel) {
        when (view.id) {
            R.id.cvAllMyAccounts -> {
                val bundle = Bundle()
                bundle.putParcelable("myAccountWalletModel", model)

                findNavController().navigate(R.id.myAccountDetailsWalletFragment,bundle)
            }
        }
    }

}