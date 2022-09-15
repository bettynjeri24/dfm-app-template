package com.ekenya.rnd.wallet.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.BillsWalletModel
import com.ekenya.rnd.wallet.data.MyAccountWalletModel
import com.ekenya.rnd.wallet.databinding.ItemUpcomingBillLayoutWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.listeners.OnBillItemClickedListener


class UpComingBillWalletAdapter(
    private val model: ArrayList<BillsWalletModel>,
    private val listener: OnBillItemClickedListener
) : ListAdapter<MyAccountWalletModel, UpComingBillWalletAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemUpcomingBillLayoutWalletBinding.inflate(
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

            if (model[position].isBillPaid) {
                btnBillsPaid.isEnabled = false
                btnBillsPaid.background = null
                btnBillsPaid.setTextColor(Color.DKGRAY)
                mcVRoout.isCheckable = false
                mcVRoout.isEnabled = false
            } else {
                btnBillsPaid.text = "Pay"
                btnBillsPaid.isEnabled = true
                btnBillsPaid.setBackgroundResource(R.drawable.btn_rounded_corners_pay_bill_wallet)
            }

            btnBillsPaid.setOnClickListener {
                listener.onBillsWalletModelClicked(it, model[position])
            }
            mcVRoout.setOnClickListener {
                listener.onBillsWalletModelClicked(it, model[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return model.size
    }

    inner class ViewHolder(val binding: ItemUpcomingBillLayoutWalletBinding) :
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
