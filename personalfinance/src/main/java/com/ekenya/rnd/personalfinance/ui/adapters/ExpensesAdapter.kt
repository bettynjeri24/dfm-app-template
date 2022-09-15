package com.ekenya.rnd.personalfinance.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.personalfinance.R
import com.ekenya.rnd.personalfinance.data.modeldto.ExpensesModel
import com.ekenya.rnd.personalfinance.databinding.ExpensesItemRowLayoutPfBinding


class ExpensesAdapter(
    private val expensesModel: ArrayList<ExpensesModel>
) : ListAdapter<ExpensesModel, ExpensesAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ExpensesItemRowLayoutPfBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        /* bind card details */
        holder.itemSelectContactMerchantBinding.apply {
            tVTitleExpense.text = expensesModel[position].titleExpense
            tVAmount.text = "Kes ${expensesModel[position].amountExpense}"
            tvExpensesPercentage.text = expensesModel[position].percentageExpense
            iVExpenses.setImageResource(expensesModel[position].imageExpense)
            root.setOnClickListener {
                it.findNavController().navigate(R.id.moreAboutExpensesFragment)
            }
        }
    }

    override fun getItemCount(): Int {
        return expensesModel.size
    }

    class ViewHolder(val itemSelectContactMerchantBinding: ExpensesItemRowLayoutPfBinding) :
        RecyclerView.ViewHolder(itemSelectContactMerchantBinding.root)

}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<ExpensesModel>() {
    override fun areItemsTheSame(oldItem: ExpensesModel, newItem: ExpensesModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ExpensesModel, newItem: ExpensesModel): Boolean {
        return oldItem == newItem
    }

}
