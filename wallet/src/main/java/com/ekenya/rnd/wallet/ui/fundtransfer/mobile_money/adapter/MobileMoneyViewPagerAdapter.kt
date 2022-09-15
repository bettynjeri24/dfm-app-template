package com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ekenya.rnd.wallet.data.ContactListWalletModel
import com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.tabs.TabAllContactsWalletFragment
import com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.tabs.TabFrequentContactsFragment

class MobileMoneyViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        lateinit var myFragment: Fragment
        when (position) {
            0 -> myFragment = TabAllContactsWalletFragment()

            1 -> {
                myFragment = TabFrequentContactsFragment()
            }
        }
        return myFragment
    }

    companion object {
        val contactHashSet = HashSet<ContactListWalletModel>()
        const val CONTACTS_LOADER_ID = 1
    }
}

private const val NUM_TABS = 2


/*
*
* private fun initUI() {
*  val tabsTitkle = arrayOf(
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
    }*/