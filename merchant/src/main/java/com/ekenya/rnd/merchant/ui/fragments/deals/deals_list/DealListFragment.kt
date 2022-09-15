package com.ekenya.rnd.merchant.ui.fragments.deals.deals_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.merchant.databinding.FragmentDealListMerchantBinding


class DealListFragment : Fragment() {

    private lateinit var binding : FragmentDealListMerchantBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDealListMerchantBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    private fun setUpUi() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.recyclerViewDeals.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val categoryAdapter = MyDealRecyclerViewAdapter()
            categoryAdapter.submitList(getDealsList())
            adapter = categoryAdapter
        }
    }

    private fun getDealsList() =  mutableListOf<Deal>().apply {
        add(Deal("Blue Band Peanut Butter 400G","KES 164.00","8% off","KES 164.00"))
        add(Deal("Blue Band Peanut Butter 400G","KES 164.00","8% off","KES 164.00"))
        add(Deal("Blue Band Peanut Butter 400G","KES 164.00","8% off","KES 164.00"))
        add(Deal("Blue Band Peanut Butter 400G","KES 164.00","8% off","KES 164.00"))
        add(Deal("Blue Band Peanut Butter 400G","KES 164.00","8% off","KES 164.00"))
        add(Deal("Blue Band Peanut Butter 400G","KES 164.00","8% off","KES 164.00"))
        add(Deal("Blue Band Peanut Butter 400G","KES 164.00","8% off","KES 164.00"))
        add(Deal("Blue Band Peanut Butter 400G","KES 164.00","8% off","KES 164.00"))
        add(Deal("Blue Band Peanut Butter 400G","KES 164.00","8% off","KES 164.00"))
        add(Deal("Blue Band Peanut Butter 400G","KES 164.00","8% off","KES 164.00"))
        add(Deal("Blue Band Peanut Butter 400G","KES 164.00","8% off","KES 164.00"))
        add(Deal("Blue Band Peanut Butter 400G","KES 164.00","8% off","KES 164.00"))
    }


}