package com.ekenya.rnd.mycards.domain.model

import com.ekenya.rnd.mycards.data.room.entities.CardEntity

data class Card(
    var id: Int,
    val cardType: String,
    val imageCardLogo: String,
    val cardNumberText: String,
    val cardExpiryDate: String
)

fun Card.toEntity() : CardEntity{
    return CardEntity(
        id = id,
        cardType = cardType,
        imageCardLogo = imageCardLogo,
        cardNumberText = cardNumberText,
        cardExpiryDate = cardExpiryDate
    )
}