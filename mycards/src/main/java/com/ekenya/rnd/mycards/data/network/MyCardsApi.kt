package com.ekenya.rnd.mycards.data.network

import com.ekenya.rnd.mycards.data.room.entities.CardEntity

interface MyCardsApi {

    suspend fun searchCards(): List<CardEntity>

}