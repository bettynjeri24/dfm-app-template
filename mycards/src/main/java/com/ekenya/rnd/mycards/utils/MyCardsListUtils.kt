package com.ekenya.rnd.mycards.utils

import com.ekenya.rnd.mycards.R

data class Options(
    val optionString: String,
    val optionImage: Int
)



data class Statement(
    val title: String,
    val date: String,
    val amount: String
)

object MyCardsListUtils {

    val options = mutableListOf<Options>().apply {
        add(Options(TOP_UP, R.drawable.ic_topup_cards))
        add(Options(TRAVEL_NOTIFICATION, R.drawable.ic_travel_cards))
        add(Options(MINI_STATEMENT, R.drawable.ic_mini_statement_cards))
        add(Options(UPDATE_LIMITS, R.drawable.ic_update_limits_cards))
        add(Options(CHANGE_PIN, R.drawable.ic_change_pin_cards))
        add(Options(BLOCK_CARD, R.drawable.ic_block_card_cards))
    }


//    val cardList = mutableListOf<Card>().apply {
//        add(
//            Card(
//                R.drawable.ic_cardbackground_cards,
//                R.drawable.ic_eclectics_cards,
//                R.drawable.ic_visalogo_card,
//                "7346   ••••   ••••  7428",
//                "12/25"
//            )
//        )
//        add(
//            Card(
//                R.drawable.ic_cardbackgroundtwo_cards,
//                R.drawable.ic_eclectis_two_cards,
//                R.drawable.ic_mastercardlogo_cards,
//                "7346   ••••   ••••  7428",
//                "12/25"
//            )
//        )
//    }

    val statements = mutableListOf<Statement>().apply {
        add(Statement("POS - Tunnel Shop", "28/01/2022 12:35pm", "- Kes 2,000.00"))
        add(Statement("Card Top-Up", "28/01/2022 12:35pm", "+ Kes 8,000.00"))
        add(Statement("POS - Tunnel Shop", "28/01/2022 12:35pm", "- Kes 2,000.00"))
        add(Statement("Card Top-Up", "28/01/2022 12:35pm", "+ Kes 8,000.00"))
    }


}

