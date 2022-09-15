package com.ekenya.rnd.wallet.ui.deposit.cheque

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentChequeDepositWalletBinding
import com.ekenya.rnd.wallet.utils.BaseWalletFragment
import com.ekenya.rnd.wallet.utils.createSuccessBundle
import com.ekenya.rnd.wallet.utils.setBackButton
import java.util.HashMap

class StartChequeDepositWalletFragment :
    BaseWalletFragment<FragmentChequeDepositWalletBinding>(FragmentChequeDepositWalletBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = getString(R.string.deposit_cheque_wallet),
                subTitle = getString(R.string.deposit_cheque_wallet),
                cardTitle = getString(R.string.deposit_cheque_wallet),
                cardContent = getString(R.string.cardContent_wallet),
                hashMap = hashMapBaseWallet
            )
        )


        binding.clToolBar.toolbar.setBackButton(R.string.cheque_deposit, requireActivity())

        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, 1)
        viewPagerAdapter.addFragment(
            Tab1DepositChequeFragmentWallet(),
            getString(R.string.deposit_cheque_wallet)
        )
        viewPagerAdapter.addFragment(
            Tab2HistoryChequeDepositWalletFragment(),
            getString(R.string.history_wallet)
        )
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

    }

    private class ViewPagerAdapter(fm: FragmentManager, behavior: Int) :
        FragmentPagerAdapter(fm, behavior) {
        val fragments: MutableList<Fragment> = ArrayList()
        private val fragmentTitles: MutableList<String> = ArrayList()

        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            fragmentTitles.add(title)
        }

        override fun getCount(): Int {
            return fragments.size
        }

        @Nullable
        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitles[position]
        }

        override fun getItem(position: Int): Fragment {
            lateinit var myFragment: Fragment
            when (position) {
                0 -> myFragment = Tab1DepositChequeFragmentWallet()

                1 -> {
                    myFragment = Tab2HistoryChequeDepositWalletFragment()
                }
            }
            return myFragment
        }


    }


    companion object {
        fun newInstance() =
            StartChequeDepositWalletFragment()
    }
}