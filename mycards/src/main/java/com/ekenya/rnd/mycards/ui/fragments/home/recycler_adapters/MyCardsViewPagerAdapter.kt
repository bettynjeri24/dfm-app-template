package com.ekenya.rnd.mycards.ui.fragments.home.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.common.form_validation.ui_extensions.formatAsHiddenCardNumber
import com.ekenya.rnd.mycards.R
import com.ekenya.rnd.mycards.data.room.entities.CardEntity
import com.ekenya.rnd.mycards.databinding.ItemBankCardsBinding

class MyCardsViewPagerAdapter : ListAdapter<CardEntity, MyCardsViewPagerAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //init binding and return root view
        val binding = ItemBankCardsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = getItem(position)
        //bind card details
        holder.cardBankcardItemBinding.apply {

            //set image in the VISA position
            imageViewCardLogo.setImageResource(getBackCardLogo(card.imageCardLogo))
            //set card background Image
            imageViewCardBackground.setImageResource(getBackgroundImage(card.cardType))
            //set the eclectics image
            imageViewEclectics.setImageResource(R.drawable.ic_eclectics_cards)
            //set card number details
            textViewBankDigits.text = card.cardNumberText.formatAsHiddenCardNumber()
            //set card expiry details
            textViewExpiry.text = card.cardExpiryDate
        }
    }

    private fun getBackCardLogo(imageCardLogo: String): Int {
        return R.drawable.ic_visalogo_card
    }

    class ViewHolder(val cardBankcardItemBinding: ItemBankCardsBinding) : RecyclerView.ViewHolder(cardBankcardItemBinding.root)

}

private fun getBackgroundImage(cardType: String): Int {
    return when(cardType){
        "Credit" -> {
            R.drawable.ic_cardbackground_cards
        }
        "Debit" -> {
            R.drawable.ic_cardbackgroundtwo_cards
        }
        else -> {
            R.drawable.ic_cardbackground_cards
        }
    }
}


//create diff util class
private val DIFF_UTIL = object : DiffUtil.ItemCallback<CardEntity>() {
    override fun areItemsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
        return oldItem == newItem
    }

}
