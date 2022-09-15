package com.ekenya.rnd.personalfinance.ui.adapters.multipleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.personalfinance.R
import com.ekenya.rnd.personalfinance.databinding.BodyExpenseLayoutPfBinding
import com.ekenya.rnd.personalfinance.databinding.HeaderExpenseLayoutPfBinding

class MoreExpenseRecyclerViewAdapter(private val items:List<MoreExpenseRecyclerViewItem>) :
    RecyclerView.Adapter<MoreExpenseRecyclerViewHolder>() {

    /* var items = listOf<MoreExpenseRecyclerViewItem>()
         set(value) {
             field = value
             notifyDataSetChanged()
         }*/

    var itemClickListener: ((view: View, item: MoreExpenseRecyclerViewItem, position: Int) -> Unit)? =
        null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoreExpenseRecyclerViewHolder {
        return when (viewType) {
            R.layout.header_expense_layout_pf -> MoreExpenseRecyclerViewHolder.HeaderMoreExpenseViewHolder(
                HeaderExpenseLayoutPfBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.body_expense_layout_pf -> MoreExpenseRecyclerViewHolder.BodyMoreExpenseViewHolder(
                BodyExpenseLayoutPfBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: MoreExpenseRecyclerViewHolder, position: Int) {
        holder.itemClickListener = itemClickListener
        when (holder) {
            is MoreExpenseRecyclerViewHolder.BodyMoreExpenseViewHolder -> holder.bind(items[position] as MoreExpenseRecyclerViewItem.BodyMoreExpense)
            is MoreExpenseRecyclerViewHolder.HeaderMoreExpenseViewHolder -> holder.bind(items[position] as MoreExpenseRecyclerViewItem.HeaderMoreExpense)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MoreExpenseRecyclerViewItem.HeaderMoreExpense -> R.layout.header_expense_layout_pf
            is MoreExpenseRecyclerViewItem.BodyMoreExpense -> R.layout.body_expense_layout_pf

        }
    }
}