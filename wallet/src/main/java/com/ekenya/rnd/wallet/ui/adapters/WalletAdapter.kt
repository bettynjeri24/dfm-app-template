package com.ekenya.rnd.wallet.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.wallet.data.BillsWalletModel
import com.ekenya.rnd.wallet.data.WalletMenuModel
import com.ekenya.rnd.wallet.databinding.ItemWalletAdapterMenusBinding


class WalletAdapter(
    private val model: List<WalletMenuModel>,
    private val listener: OnWalletMenuItemClickedListener
) : ListAdapter<WalletMenuModel, WalletAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemWalletAdapterMenusBinding.inflate(
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

        holder.binding.apply {
            tvMenuName.text = model[position].menuName
            ivMenuIcon.setImageResource(model[position].menuImg)
            cvWalletMenu.setOnClickListener {
                listener.onWalletMenuClicked(it, model = model[position])
            }
        }


    }

    override fun getItemCount(): Int {
        return model.size
    }

    inner class ViewHolder(val binding: ItemWalletAdapterMenusBinding) :
        RecyclerView.ViewHolder(binding.root)


}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<WalletMenuModel>() {
    override fun areItemsTheSame(
        oldItem: WalletMenuModel,
        newItem: WalletMenuModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: WalletMenuModel,
        newItem: WalletMenuModel
    ): Boolean {
        return oldItem == newItem
    }

}


interface OnWalletMenuItemClickedListener {
    fun onWalletMenuClicked(view: View, model: WalletMenuModel)
}