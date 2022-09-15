package com.ekenya.rnd.merchant.ui.fragments.direct_payment.bottom_sheet_merchant_payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.merchant.databinding.ItemMethodOfPaymentMerchantBinding
import com.ekenya.rnd.merchant.databinding.ItemMycardsBinding
import com.ekenya.rnd.merchant.utils.MyCards

/**
 * Shows a list of my owned cards
 */
class AdapterRecycleviewMyCards : ListAdapter<MyCards, AdapterRecycleviewMyCards.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemMycardsBinding.inflate(
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
        val card = getItem(position)
        holder.itemMycardsBinding.apply {
            textViewCardType.text = card.cardType
            textViewCardNumber.text = card.cardNumber
            textViewName.text = card.name
            imageViewVisa.setImageResource(card.visa)
        }
    }

    class ViewHolder(val itemMycardsBinding: ItemMycardsBinding) :
        RecyclerView.ViewHolder(itemMycardsBinding.root)

}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<MyCards>() {
    override fun areItemsTheSame(oldItem: MyCards, newItem: MyCards): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MyCards, newItem: MyCards): Boolean {
        return oldItem == newItem
    }

}