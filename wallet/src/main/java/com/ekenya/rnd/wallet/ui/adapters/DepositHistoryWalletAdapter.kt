package com.ekenya.rnd.wallet.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.wallet.data.DepositHistoryWalletModel
import com.ekenya.rnd.wallet.databinding.ItemDepositHistoryLayoutWalletBinding


class DepositHistoryWalletAdapter(
    private val model: ArrayList<DepositHistoryWalletModel>
) : ListAdapter<DepositHistoryWalletModel, DepositHistoryWalletAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemDepositHistoryLayoutWalletBinding.inflate(
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
            tVTitleDepositHistory.text = model[position].titleDepositHistory
            tVDescDepositHistory.text = model[position].descDepositHistory
            tVDepositHistoryDateTime.text = model[position].dateTimeOfDepositHistory
            iVDepositHistory.setImageResource(model[position].imageDepositHistory)

            if (model[position].isDepositHistorySuccessful) {
                val color = "36B51F".toInt(16) + -0x1000000
                tVTitleDepositHistory.setTextColor(color)
            }
        }
    }

    override fun getItemCount(): Int {
        return model.size
    }

    inner class ViewHolder(val binding: ItemDepositHistoryLayoutWalletBinding) :
        RecyclerView.ViewHolder(binding.root)

}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<DepositHistoryWalletModel>() {
    override fun areItemsTheSame(
        oldItem: DepositHistoryWalletModel,
        newItem: DepositHistoryWalletModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: DepositHistoryWalletModel,
        newItem: DepositHistoryWalletModel
    ): Boolean {
        return oldItem == newItem
    }

}
