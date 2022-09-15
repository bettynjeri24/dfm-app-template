package com.ekenya.rnd.wallet.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.wallet.data.MiniStatementWalletModel
import com.ekenya.rnd.wallet.databinding.ItemMinistatementAccountLayoutWalletBinding


class MiniStatementAccountWalletAdapter(
    private val model: ArrayList<MiniStatementWalletModel>
) : ListAdapter<MiniStatementWalletModel, MiniStatementAccountWalletAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemMinistatementAccountLayoutWalletBinding.inflate(
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
        holder.binding.apply {
            tVTitleMiniStatement.text = model[position].titleMiniStatement
            tVMiniStatementDateTime.text = model[position].dateTimeOfMiniStatement
            tVMiniStatementAmount.text = model[position].amountMiniStatement
            iVMiniStatement.setImageResource(model[position].imageMiniStatement)
            if (model[position].isMiniStatementCashOut) {
                val color = "36B51F".toInt(16) + -0x1000000
                tVMiniStatementAmount.setTextColor(color)
            }
        }
    }

    override fun getItemCount(): Int {
        return model.size
    }

    inner class ViewHolder(val binding: ItemMinistatementAccountLayoutWalletBinding) :
        RecyclerView.ViewHolder(binding.root)

}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<MiniStatementWalletModel>() {
    override fun areItemsTheSame(
        oldItem: MiniStatementWalletModel,
        newItem: MiniStatementWalletModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MiniStatementWalletModel,
        newItem: MiniStatementWalletModel
    ): Boolean {
        return oldItem == newItem
    }

}
