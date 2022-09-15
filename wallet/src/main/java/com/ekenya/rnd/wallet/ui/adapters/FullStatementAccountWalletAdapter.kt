package com.ekenya.rnd.wallet.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.wallet.data.FullStatementWalletModel
import com.ekenya.rnd.wallet.databinding.ItemFullstatementAccountLayoutWalletBinding


class FullStatementAccountWalletAdapter(
    private val model: ArrayList<FullStatementWalletModel>
) : ListAdapter<FullStatementWalletModel, FullStatementAccountWalletAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemFullstatementAccountLayoutWalletBinding.inflate(
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
            tVTitleFullStatement.text = model[position].titleFullStatement
            iVFullStatementIcon.setImageResource(model[position].imageIconFullStatement)
            if (model[position].isFullStatementDownloaded) {
                iVFullStatementDownload.isVisible = false
                tvFullStatementDownloadedOpen.isVisible = true
            } else {
                iVFullStatementDownload.isVisible = true
                tvFullStatementDownloadedOpen.isVisible = false
                iVFullStatementDownload.setImageResource(model[position].imageDownloadFullStatement)
            }

        }
    }

    override fun getItemCount(): Int {
        return model.size
    }

    inner class ViewHolder(val binding: ItemFullstatementAccountLayoutWalletBinding) :
        RecyclerView.ViewHolder(binding.root)

}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<FullStatementWalletModel>() {
    override fun areItemsTheSame(
        oldItem: FullStatementWalletModel,
        newItem: FullStatementWalletModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: FullStatementWalletModel,
        newItem: FullStatementWalletModel
    ): Boolean {
        return oldItem == newItem
    }

}
