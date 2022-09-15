package com.ekenya.rnd.wallet.ui.myaccount.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentTabHolderMyAccountsWalletBinding
import com.ekenya.rnd.wallet.ui.myaccount.tabs.dormant.Tab3DormantMyAccountFragment
import com.ekenya.rnd.wallet.ui.myaccount.tabs.linked.Tab1LinkedMyAccountFragment
import com.ekenya.rnd.wallet.ui.myaccount.tabs.unlink.Tab2UnLinkedMyAccountFragment
import com.ekenya.rnd.wallet.utils.BaseWalletFragment
import com.ekenya.rnd.wallet.utils.setBackButton
import com.google.android.material.tabs.TabLayoutMediator


class TabHolderMyAccountsFragment : BaseWalletFragment<FragmentTabHolderMyAccountsWalletBinding>(
    FragmentTabHolderMyAccountsWalletBinding::inflate
) {
    private val accountTypesArray = arrayOf(
        "Linked",
        "Unlinked",
        "Dormant"
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clToolBar.toolbar.setBackButton(
            (R.string.my_account_wallet),
            requireActivity()
        )
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = MyAccountsViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = accountTypesArray[position]
        }.attach()
    }
}


class MyAccountsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {

        lateinit var myFragment: Fragment
        when (position) {
            0 -> myFragment = Tab1LinkedMyAccountFragment()

            1 -> {
                myFragment = Tab2UnLinkedMyAccountFragment()
            }
            2 -> {
                myFragment = Tab3DormantMyAccountFragment()
            }
        }
        return myFragment
    }
}


private const val NUM_TABS = 3