package com.ekenya.rnd.wallet.ui.bills.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentBillPaymentWalletBinding
import com.ekenya.rnd.wallet.utils.setBackButton

class BillPaymentWalletFragment : Fragment() {

    private lateinit var binding: FragmentBillPaymentWalletBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBillPaymentWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clToolBar.toolbar.setBackButton(R.string.bills_title_wallet, requireActivity())

        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, 1)
        viewPagerAdapter.addFragment(
            Tab1AllBillPaymentWalletFragment(),
            getString(R.string.all_bills_wallet)
        )
        viewPagerAdapter.addFragment(
            Tab2UpcomingBillsPaymentWalletFragment(),
            getString(R.string.upcoming_bills_wallet)
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
                0 -> myFragment = Tab1AllBillPaymentWalletFragment()

                1 -> {
                    myFragment = Tab2UpcomingBillsPaymentWalletFragment()
                }
            }
            return myFragment
        }


    }


    companion object {
        fun newInstance() =
            BillPaymentWalletFragment()
    }
}