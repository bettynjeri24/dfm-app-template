package com.ekenya.rnd.merchant.data.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ekenya.rnd.merchant.data.room.daos.ContactsDao
import com.ekenya.rnd.merchant.data.room.entities.ContactEntity

@Database(
    entities = [ContactEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MerchantDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao

    companion object {

        // mark volatile for thread safety
        @Volatile
        private var instance: MerchantDatabase? = null

        // make a lock
        private val LOCK = Any()

        // create a db instance if none exist and assign it to @instance
        operator fun invoke(application: Application) = instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(application).also {
                    instance = it
                }
            }

        // build the database
        private fun buildDatabase(application: Application) = Room.databaseBuilder(application.applicationContext, MerchantDatabase::class.java, "merchant_database").build()

    }
}