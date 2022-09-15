package com.ekenya.rnd.wallet.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.wallet.data.AccountDetailsWalletModel
import com.ekenya.rnd.wallet.databinding.ItemAccountDetailsWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.listeners.OnAccountsDetailsItemClickedListener


class AccountsDetailsWalletAdapter(
    private val model: ArrayList<AccountDetailsWalletModel>,
    private val listener: OnAccountsDetailsItemClickedListener
) : ListAdapter<AccountDetailsWalletModel, AccountsDetailsWalletAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemAccountDetailsWalletBinding.inflate(
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
            tVTitleAccountDetails.text = model[position].titleAccountDetails
            iVAccountDetails.setImageResource(model[position].imageAccountDetails)
            cvAccountDetails.setOnClickListener {
                listener.onClickedItem(it, model[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return model.size
    }

    inner class ViewHolder(val binding: ItemAccountDetailsWalletBinding) :
        RecyclerView.ViewHolder(binding.root)

}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<AccountDetailsWalletModel>() {
    override fun areItemsTheSame(
        oldItem: AccountDetailsWalletModel,
        newItem: AccountDetailsWalletModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: AccountDetailsWalletModel,
        newItem: AccountDetailsWalletModel
    ): Boolean {
        return oldItem == newItem
    }

}
