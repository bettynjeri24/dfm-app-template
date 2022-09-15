package com.ekenya.rnd.personalfinance.ui.budgets.expenses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ekenya.rnd.personalfinance.data.repos.ExpensesRepository
import com.ekenya.rnd.personalfinance.ui.adapters.multipleview.MoreExpenseRecyclerViewItem
import javax.inject.Inject

class ExpensesViewModel @Inject constructor(
    private val expensesRepository: ExpensesRepository
) : ViewModel() {

    private val _homeListItemsLiveData = MutableLiveData<List<MoreExpenseRecyclerViewItem>>()
    val homeListItemsLiveData: LiveData<List<MoreExpenseRecyclerViewItem>>
        get() = _homeListItemsLiveData

    private val _homeItemsLiveData =
        MutableLiveData<MoreExpenseRecyclerViewItem.BodyMoreExpense>()
    val homeItemsLiveData: LiveData<MoreExpenseRecyclerViewItem.BodyMoreExpense>
        get() = _homeItemsLiveData

    init {
        //getHomeListItems()
    }

//
//    private fun getHomeListItems() = viewModelScope.launch {
//        val sliderItems = MoreExpenseRecyclerViewItem.HeaderMoreExpense("")
//
//        //val sliderItems = directorsDeferred
//
//        val homeItemsList = mutableListOf<MoreExpenseRecyclerViewItem>()
//
//        homeItemsList.add(MoreExpenseRecyclerViewItem.HeaderMoreExpense(""))
//
//        homeItemsList.addAll(sliderItems)
//
//    }
}
