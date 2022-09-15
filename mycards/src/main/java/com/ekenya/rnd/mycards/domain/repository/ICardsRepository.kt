package com.ekenya.rnd.mycards.domain.repository

import com.ekenya.rnd.mycards.data.room.entities.CardEntity
import kotlinx.coroutines.flow.Flow

interface ICardsRepository {

    suspend fun cacheCards(cards : List<CardEntity>)

    suspend fun cacheCard(cardEntity: CardEntity)

    fun getCards() : Flow<List<CardEntity>>

    fun getCard(string: String) : Flow<CardEntity>

    suspend fun fetchCardsRemote() : List<CardEntity>

}