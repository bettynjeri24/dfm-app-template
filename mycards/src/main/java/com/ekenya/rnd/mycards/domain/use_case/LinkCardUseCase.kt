package com.ekenya.rnd.mycards.domain.use_case

import com.ekenya.rnd.mycards.data.repository.CardsRepositoryImpl
import com.ekenya.rnd.mycards.data.room.entities.CardEntity
import javax.inject.Inject


typealias LinkCardBaseUseCase = BaseUseCase<CardEntity, Unit>

class LinkCardUseCase @Inject constructor(
   private val cardsRepositoryImpl: CardsRepositoryImpl
) : LinkCardBaseUseCase {

    override suspend fun invoke(params: CardEntity) {
        cardsRepositoryImpl.cacheCard(params)
    }

}