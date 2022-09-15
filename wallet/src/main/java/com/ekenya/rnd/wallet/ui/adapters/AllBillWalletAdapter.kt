package com.ekenya.rnd.wallet.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.wallet.data.BillsWalletModel
import com.ekenya.rnd.wallet.data.MyAccountWalletModel
import com.ekenya.rnd.wallet.databinding.ItemBillLayoutWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.listeners.OnBillItemClickedListener


class AllBillWalletAdapter(
    private val model: ArrayList<BillsWalletModel>,
    private val listener: OnBillItemClickedListener
) : ListAdapter<MyAccountWalletModel, AllBillWalletAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemBillLayoutWalletBinding.inflate(
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
            tVTitleBill.text = model[position].titleBills
            tVBillDesc.text = model[position].descBills
            iVBills.setImageResource(model[position].imageBills)
            mcVRoout.setOnClickListener {
                listener.onBillsWalletModelClicked(it, model[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return model.size
    }

    inner class ViewHolder(val binding: ItemBillLayoutWalletBinding) :
        RecyclerView.ViewHolder(binding.root)

}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<MyAccountWalletModel>() {
    override fun areItemsTheSame(
        oldItem: MyAccountWalletModel,
        newItem: MyAccountWalletModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MyAccountWalletModel,
        newItem: MyAccountWalletModel
    ): Boolean {
        return oldItem == newItem
    }

}
