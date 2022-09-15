package com.ekenya.rnd.mycards.ui.fragments.home.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.mycards.databinding.ItemCardOptionsCardsBinding
import com.ekenya.rnd.mycards.utils.*
import com.ekenya.rnd.mycards.utils.HomeOption.*

/**
 * This adapter is what shows a gridview of options in the home page
 */
class OptionsAdapter(private val onClick: (HomeOption) -> Unit) : ListAdapter<Options, OptionsAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //create binding
        val cardOptionItemsBinding = ItemCardOptionsCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(cardOptionItemsBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val option = getItem(position)

        holder.cardOptionItemsBinding.apply {

            //Bind image
            imageViewOption.load(option.optionImage)

            //Bind text
            textViewOption.text = option.optionString

            //on Item clicked
            root.setOnClickListener {
                onClick.invoke(handleItemClicked(option.optionString))
            }

        }
    }

    class ViewHolder(val cardOptionItemsBinding: ItemCardOptionsCardsBinding) :
        RecyclerView.ViewHolder(cardOptionItemsBinding.root)

    /**
     * Returns types of HomeOption sealed class
     */
    private fun handleItemClicked(itemString: String): HomeOption {

        when (itemString) {

            //Top up selected
            TOP_UP -> return TopUp

            //Travel notification selected
            TRAVEL_NOTIFICATION -> return Travel

            //mini statement selected
            MINI_STATEMENT-> return MiniStatement

            //update limits selected
            UPDATE_LIMITS -> return UpdateLimits

            //change pin selected
            CHANGE_PIN -> return ChangePin

            //block card selected
            BLOCK_CARD -> return BlockCard

        }

        //something goes wrong
        return NotFound

    }
}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<Options>() {

    override fun areItemsTheSame(oldItem: Options, newItem: Options): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Options, newItem: Options): Boolean {
        return oldItem == newItem
    }

}
