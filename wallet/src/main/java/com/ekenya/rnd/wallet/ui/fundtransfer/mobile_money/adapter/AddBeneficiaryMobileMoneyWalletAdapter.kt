package com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.ContactListWalletModel
import com.ekenya.rnd.wallet.databinding.ItemBeneficiaryMmLayoutWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.listeners.OnContactItemClickedListener
import com.ekenya.rnd.wallet.utils.getFirstLetter

class AddBeneficiaryMobileMoneyWalletAdapter(
    private var listener: OnContactItemClickedListener
) : ListAdapter<ContactListWalletModel, AddBeneficiaryMobileMoneyWalletAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemBeneficiaryMmLayoutWalletBinding.inflate(
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
            val contactItem = getItem(position)
            tVTitleAddBeneficiary.text = contactItem.titleContactName
            if (contactItem.titleContactName == "Add Beneficiary") {
                tvAddBeneficiary.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    R.drawable.ic_round_add_wallet,
                    0,
                    0
                )
            } else {
                if (contactItem.titleContactName.isEmpty()) {
                    tvAddBeneficiary.visibility = View.GONE
                    iVAddBeneficiary.visibility = View.VISIBLE
                    tVTitleAddBeneficiary.text = contactItem.phoneContact
                } else {
                    tvAddBeneficiary.text = contactItem.titleContactName.getFirstLetter()
                }
            }
            mcVRoot.setOnClickListener {
                listener.onClickedItem(it, model = contactItem)
            }
        }
    }

    inner class ViewHolder(val binding: ItemBeneficiaryMmLayoutWalletBinding) :
        RecyclerView.ViewHolder(binding.root)
}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<ContactListWalletModel>() {
    override fun areItemsTheSame(
        oldItem: ContactListWalletModel,
        newItem: ContactListWalletModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ContactListWalletModel,
        newItem: ContactListWalletModel
    ): Boolean {
        return oldItem == newItem
    }
}
