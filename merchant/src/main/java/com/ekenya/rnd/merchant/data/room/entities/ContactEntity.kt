package com.ekenya.rnd.merchant.data.room.entities

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey(autoGenerate = false)
    var id: String ,
    var name: String? = null,
    var mobileNumber: String? = null,
//    var photoURI: Uri? = null,
)