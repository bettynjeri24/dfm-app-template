package com.ekenya.rnd.merchant.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.merchant.databinding.ItemHomeoptionsMerchantBinding
import com.ekenya.rnd.merchant.utils.*

/**
 * Displays the options on the home page
 */
class AdapterMerchantChoices(private val onClick : (MerchantHomeOptionState) -> Unit) : ListAdapter<MerchantOptions, AdapterMerchantChoices.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeoptionsMerchantBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = getItem(position)
        //bind card details
        holder.cardBankcarditemBinding.apply {
            imageViewOption.setImageResource(option.icon)
            textViewTitle.text = option.title
            textViewSubTitle.text = option.subtitle
            root.setOnClickListener {
                when (option.title) {

                    DIRECT_PAYMENT -> {
                        onClick.invoke(MerchantHomeOptionState.DirectPayment)
                    }
                    SCAN_PAY -> {
                        onClick.invoke(MerchantHomeOptionState.ScanAndPay)
                    }

                    EVENTS_TICKETS -> {
                        onClick.invoke(MerchantHomeOptionState.EventsAndTickets)
                    }

                    HOT_DEALS -> {
                        onClick.invoke(MerchantHomeOptionState.HotDeals)
                    }

                }
            }
        }
    }


    class ViewHolder(val cardBankcarditemBinding: ItemHomeoptionsMerchantBinding) : RecyclerView.ViewHolder(cardBankcarditemBinding.root)



}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<MerchantOptions>() {
    override fun areItemsTheSame(oldItem: MerchantOptions, newItem: MerchantOptions): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MerchantOptions, newItem: MerchantOptions): Boolean {
        return oldItem == newItem
    }

}
