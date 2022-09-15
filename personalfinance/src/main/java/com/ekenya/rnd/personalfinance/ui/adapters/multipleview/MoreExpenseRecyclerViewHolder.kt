package com.ekenya.rnd.personalfinance.ui.adapters.multipleview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ekenya.rnd.personalfinance.databinding.BodyExpenseLayoutPfBinding
import com.ekenya.rnd.personalfinance.databinding.HeaderExpenseLayoutPfBinding


sealed class MoreExpenseRecyclerViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    var itemClickListener: ((view: View, item: MoreExpenseRecyclerViewItem, position: Int) -> Unit)? =
        null

    class HeaderMoreExpenseViewHolder(private val binding: HeaderExpenseLayoutPfBinding) :
        MoreExpenseRecyclerViewHolder(binding) {
        fun bind(sliderModel: MoreExpenseRecyclerViewItem.HeaderMoreExpense) {
            binding.tvAssetName.text = sliderModel.durationMoreExpense
        }
    }

    class BodyMoreExpenseViewHolder(private val binding: BodyExpenseLayoutPfBinding) :
        MoreExpenseRecyclerViewHolder(binding) {
        fun bind(productDataRes: MoreExpenseRecyclerViewItem.BodyMoreExpense) {
            binding.tVTitleExpense.text = productDataRes.titleMoreExpense
            binding.tVTimeExpenseWasDone.text = productDataRes.timeMoreExpense
            binding.tvExpensesAmount.text = "-kes ${productDataRes.amountMoreExpense}"
            binding.root.setOnClickListener {
                itemClickListener?.invoke(it, productDataRes, adapterPosition)
            }

        }
    }

}