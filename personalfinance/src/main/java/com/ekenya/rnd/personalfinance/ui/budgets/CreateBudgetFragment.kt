package com.ekenya.rnd.personalfinance.ui.budgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.ekenya.rnd.personalfinance.R
import com.ekenya.rnd.personalfinance.databinding.FragmentCreateBudgetPfBinding
import com.ekenya.rnd.personalfinance.utils.setBackButton

class CreateBudgetFragment : Fragment() {


    private lateinit var binding: FragmentCreateBudgetPfBinding


    override fun onResume() {
        super.onResume()
        val budgetCycle = resources.getStringArray(R.array.budget_cycle)
        val arrayAdapterBudgetCycle = ArrayAdapter(
            requireContext(), R.layout.text_layout_pf, budgetCycle
        )
        binding.aCtCycle.setAdapter(arrayAdapterBudgetCycle)

        val budgetCategories = resources.getStringArray(R.array.budget_categories)
        val arrayAdapterCategories =
            ArrayAdapter(requireContext(), R.layout.text_layout_pf, budgetCategories)
        binding.aCtSelectCategories.setAdapter(arrayAdapterCategories)

        val budgetAccount = resources.getStringArray(R.array.budget_account)
        val arrayAdapterAccount =
            ArrayAdapter(requireContext(), R.layout.text_layout_pf, budgetAccount)
        binding.aCtSelectAccount.setAdapter(arrayAdapterAccount)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBudgetPfBinding.inflate(inflater, container, false)
        setUi()

        return binding.root
    }

    private fun setUi() {
        binding.toolbar.setBackButton(R.string.budgets, requireActivity())
    }


}