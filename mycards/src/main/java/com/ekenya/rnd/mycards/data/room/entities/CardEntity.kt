package com.ekenya.rnd.mycards.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ekenya.rnd.mycards.domain.model.Card

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val cardType: String,
    val imageCardLogo: String,
    val cardNumberText: String,
    val cardExpiryDate: String
)

public fun CardEntity.toDomain() : Card{
    return Card(
        id = id,
        cardType = cardType,
        imageCardLogo = imageCardLogo,
        cardNumberText = cardNumberText,
        cardExpiryDate = cardExpiryDate
    )
}