package com.ekenya.rnd.mycards.ui.fragments.mini_statement

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.mycards.R
import com.ekenya.rnd.mycards.databinding.ItemMinistatementCardsBinding
import com.ekenya.rnd.mycards.utils.HomeOption
import com.ekenya.rnd.mycards.utils.Statement

/**
 * This adapter populates items of transactions in mini statement fragment
 */
class AdapterMiniStatement : ListAdapter<Statement, AdapterMiniStatement.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemMinistatementBinding =
                ItemMinistatementCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemMinistatementBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val statement = getItem(position)

        holder.itemMinistatementBinding.apply {
            //bind item details
            textViewTitle.text = statement.title
            textViewDate.text = statement.date
            textViewAmount.text = statement.amount

            //perform conditional formatting on the item
            if (statement.amount.contains("+")){
                imageViewType.setImageResource(R.drawable.ic_deposit_cards)
                textViewAmount.setTextColor(Color.parseColor("#36B51F"))
            }
        }
    }

    class ViewHolder(val itemMinistatementBinding: ItemMinistatementCardsBinding) :
        RecyclerView.ViewHolder(itemMinistatementBinding.root)

    /**
     * Returns types of HomeOption sealed class
     */
    private fun handleItemClicked(itemString: String): HomeOption {

        when (itemString) {
            "Top-Up Card" -> return HomeOption.TopUp
            "Travel Notification" -> return HomeOption.Travel
            "Mini-statement" -> return HomeOption.MiniStatement
            "Update Limits" -> return HomeOption.UpdateLimits
            "Change Pin" -> return HomeOption.ChangePin
            "Block Card" -> return HomeOption.BlockCard
        }
        return HomeOption.NotFound
    }
}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<Statement>() {
    override fun areItemsTheSame(oldItem: Statement, newItem: Statement): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Statement, newItem: Statement): Boolean {
        return oldItem == newItem
    }

}