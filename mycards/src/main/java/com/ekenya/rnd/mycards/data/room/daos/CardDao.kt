package com.ekenya.rnd.mycards.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ekenya.rnd.mycards.data.room.entities.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    //insert a list of cards
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCards(cards: List<CardEntity>)

    //inserts one card
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCard(card : CardEntity)

    //get a flow of cards
    @Query("SELECT * FROM cards")
    fun getCards(): Flow<List<CardEntity>>

    //query one card
    @Query("SELECT * FROM cards where id = :id")
    suspend fun getCard(id : String): CardEntity

}