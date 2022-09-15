package com.ekenya.rnd.wallet.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.ContactListWalletModel
import com.ekenya.rnd.wallet.databinding.ItemBeneficiaryAirtimeLayoutWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.listeners.OnContactItemClickedListener
import com.ekenya.rnd.wallet.utils.getFirstLetter


class AddBeneficiaryWalletAdapter(
    private val model: ArrayList<ContactListWalletModel>,
    private var listener: OnContactItemClickedListener
) : ListAdapter<ContactListWalletModel, AddBeneficiaryWalletAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemBeneficiaryAirtimeLayoutWalletBinding.inflate(
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
            tVTitleAddBeneficiary.text = model[position].titleContactName
            if (model[position].titleContactName == "Add Beneficiary") {
                iVAddBeneficiary.setCompoundDrawablesWithIntrinsicBounds(
                   0,  R.drawable.ic_round_add_wallet, 0, 0
                );
            } else {
                iVAddBeneficiary.text = model[position].titleContactName.getFirstLetter()
            }
            mcVRoot.setOnClickListener {
                listener.onClickedItem(it, model = model[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return model.size
        //return 2
    }

    inner class ViewHolder(val binding: ItemBeneficiaryAirtimeLayoutWalletBinding) :
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
