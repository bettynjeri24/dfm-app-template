package com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.link_card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekenya.rnd.mycards.data.room.entities.CardEntity
import com.ekenya.rnd.mycards.domain.use_case.LinkCardUseCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LinkCardViewModel @Inject constructor(
    private val linkCardUseCase: LinkCardUseCase
) : ViewModel() {

    fun addCards(card: CardEntity) {
        viewModelScope.launch {
            linkCardUseCase.invoke(card)
        }
    }

}