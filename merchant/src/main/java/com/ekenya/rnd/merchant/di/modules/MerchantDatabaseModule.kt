package com.ekenya.rnd.merchant.di.modules

import android.app.Application
import com.ekenya.rnd.merchant.data.room.MerchantDatabase
import com.ekenya.rnd.merchant.data.room.daos.ContactsDao
import dagger.Module
import dagger.Provides

@Module
class MerchantDatabaseModule {

    @Provides
    fun providesContactsDao(merchantDatabase: MerchantDatabase) : ContactsDao{
        return merchantDatabase.contactsDao()
    }

    @Provides
    fun providesDB(application: Application): MerchantDatabase {
        return MerchantDatabase.invoke(application)
    }

}