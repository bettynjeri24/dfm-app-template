package com.ekenya.rnd.mycards.ui.fragments.home

import androidx.lifecycle.*
import com.ekenya.rnd.mycards.data.repository.CardsRepositoryImpl
import com.ekenya.rnd.mycards.data.room.entities.CardEntity
import javax.inject.Inject

class MyCardsHomeViewModel @Inject constructor(
    private val cardsRepositoryImpl: CardsRepositoryImpl
) : ViewModel() {

    val favoriteViewState: LiveData<CardEntity>
        get() = _favoriteViewState

    private var _favoriteViewState = MutableLiveData<CardEntity>()

    val cards = cardsRepositoryImpl.cards().asLiveData().distinctUntilChanged()

}