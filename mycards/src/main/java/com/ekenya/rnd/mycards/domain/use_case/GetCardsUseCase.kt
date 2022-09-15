package com.ekenya.rnd.mycards.domain.use_case

import com.ekenya.rnd.mycards.data.repository.CardsRepositoryImpl
import com.ekenya.rnd.mycards.data.room.entities.CardEntity
import com.ekenya.rnd.mycards.utils.Resource
import com.ekenya.rnd.mycards.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetCardBaseUseCase = BaseUseCase<Unit, Flow<List<CardEntity>>>

class GetCardsUseCase @Inject constructor(
    private val cardRepository: CardsRepositoryImpl
) : GetCardBaseUseCase {

    override suspend fun invoke(params: Unit): Flow<List<CardEntity>> = cardRepository.getCards()

}