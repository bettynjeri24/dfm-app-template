package com.ekenya.rnd.merchant.data.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ekenya.rnd.mycards.data.room.daos.CardDao
import com.ekenya.rnd.mycards.data.room.entities.CardEntity

@Database(
    entities = [CardEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MyCardsDatabase : RoomDatabase() {

    abstract fun cardsDao(): CardDao

    companion object {

        @Volatile
        private var instance: MyCardsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(application: Application) = instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(application).also {
                    instance = it
                }
            }

        private fun buildDatabase(application: Application) = Room.databaseBuilder(application.applicationContext, MyCardsDatabase::class.java, "merchant_database").build()

    }
}