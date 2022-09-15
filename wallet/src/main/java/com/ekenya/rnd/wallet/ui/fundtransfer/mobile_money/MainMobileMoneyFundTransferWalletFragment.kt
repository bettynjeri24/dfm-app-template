package com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money

import android.os.Bundle
import android.view.View
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentMainMobileMoneyFtWalletBinding
import com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.adapter.MobileMoneyViewPagerAdapter
import com.ekenya.rnd.wallet.utils.BaseWalletFragment
import com.ekenya.rnd.wallet.utils.setBackButton
import com.google.android.material.tabs.TabLayoutMediator

class MainMobileMoneyFundTransferWalletFragment :
    BaseWalletFragment<FragmentMainMobileMoneyFtWalletBinding>(
        FragmentMainMobileMoneyFtWalletBinding::inflate
    ) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        val tabsTitkle = arrayOf(
            "All Contacts",
            "Frequent contacts"
        )
        binding.clToolBar.toolbar.setBackButton(
            (R.string.send_money_to_mpesa_wallet),
            requireActivity()
        )
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = MobileMoneyViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabsTitkle[position]
        }.attach()
    }
}
