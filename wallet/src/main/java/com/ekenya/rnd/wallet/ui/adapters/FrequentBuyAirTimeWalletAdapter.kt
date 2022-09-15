package com.ekenya.rnd.wallet.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.wallet.data.ContactListWalletModel
import com.ekenya.rnd.wallet.databinding.ItemFrequentsAirtimeLayoutWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.listeners.OnContactItemClickedListener
import com.ekenya.rnd.wallet.utils.getFirstLetter


class FrequentBuyAirTimeWalletAdapter(
    private val model: ArrayList<ContactListWalletModel>,
    private var listener: OnContactItemClickedListener
) : ListAdapter<ContactListWalletModel, FrequentBuyAirTimeWalletAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemFrequentsAirtimeLayoutWalletBinding.inflate(
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
            tVTitleContactList.text = model[position].titleContactName
            iVContactList.text = model[position].titleContactName.getFirstLetter()
            tVContactListDesc.text = model[position].phoneContact
            mcVRoot.setOnClickListener {
                listener.onClickedItem(it, model = model[position])
                // it.findNavController().navigate(R.id.buyAirTimeWalletFragment)
            }
        }

    }

    override fun getItemCount(): Int {
        return 2
        //return model.size
    }

    inner class ViewHolder(val binding: ItemFrequentsAirtimeLayoutWalletBinding) :
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
