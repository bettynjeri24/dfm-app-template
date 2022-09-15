package com.ekenya.rnd.merchant.data.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ekenya.rnd.merchant.data.room.entities.ContactEntity

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveContacts(candidates: List<ContactEntity>): List<Long>

    @Query("SELECT * FROM contacts")
    fun getContacts(): LiveData<List<ContactEntity>>

}