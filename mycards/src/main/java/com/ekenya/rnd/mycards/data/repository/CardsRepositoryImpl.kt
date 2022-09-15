package com.ekenya.rnd.mycards.data.repository

import androidx.room.withTransaction
import com.ekenya.rnd.merchant.data.room.MyCardsDatabase
import com.ekenya.rnd.mycards.data.network.MyCardsApi
import com.ekenya.rnd.mycards.data.room.entities.CardEntity
import com.ekenya.rnd.mycards.domain.repository.ICardsRepository
import com.ekenya.rnd.mycards.utils.Resource
import com.ekenya.rnd.mycards.utils.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CardsRepositoryImpl @Inject constructor(
    private val db: MyCardsDatabase,
    private val myCardsApi: MyCardsApi
) : ICardsRepository {

    private val cardDao = db.cardsDao()

    fun cards(): Flow<Resource<List<CardEntity>>> = networkBoundResource(
        queryDao = {
            cardDao.getCards()
        },
        fetchApi = {
            myCardsApi.searchCards()
        },
        saveFetchResult = { cards ->
            cacheCards(cards)
        },
        shouldFetch = { false }
    )

    override suspend fun cacheCards(cards: List<CardEntity>) {
        db.withTransaction {
            cardDao.saveCards(cards)
        }
    }

    override suspend fun cacheCard(cardEntity: CardEntity) {
        db.withTransaction {
            cardDao.saveCard(cardEntity)
        }
    }

    override fun getCards(): Flow<List<CardEntity>> {
           return cardDao.getCards()
    }


    override suspend fun fetchCardsRemote(): List<CardEntity> {
        return mutableListOf()
    }

    override  fun getCard(string: String): Flow<CardEntity> = flow {
         emit(cardDao.getCard(string))
    }


}