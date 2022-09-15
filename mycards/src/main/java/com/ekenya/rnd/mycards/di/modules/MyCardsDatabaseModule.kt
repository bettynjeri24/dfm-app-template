package com.ekenya.rnd.mycards.di.modules

import android.app.Application
import com.ekenya.rnd.merchant.data.room.MyCardsDatabase
import com.ekenya.rnd.mycards.data.room.daos.CardDao
import dagger.Module
import dagger.Provides

@Module
class MyCardsDatabaseModule {

    @Provides
    fun providesCardsDao(myCardsDatabase: MyCardsDatabase) : CardDao{
        return myCardsDatabase.cardsDao()
    }

    @Provides
    fun providesDB(application: Application): MyCardsDatabase {
        return MyCardsDatabase(application)
    }
}