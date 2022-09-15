package com.ekenya.rnd.personalfinance.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekenya.rnd.personalfinance.data.repos.ExpensesRepository
import com.ekenya.rnd.personalfinance.ui.adapters.multipleview.MoreExpenseRecyclerViewItem
import kotlinx.coroutines.launch

class SharedViewModel :
    ViewModel() {
    /**
     *Utilizing  MutableLiveData of AssetDataModel
     */
    private var _assetData =
        MutableLiveData<MutableList<MoreExpenseRecyclerViewItem.BodyMoreExpense>>()
    val assetData get() = _assetData
    private val list = ArrayList<MoreExpenseRecyclerViewItem.BodyMoreExpense>()

    fun deleteAssetData() {
        list.clear()
    }

    fun deleteSingleAssetData(assetDataModel: MoreExpenseRecyclerViewItem.BodyMoreExpense) {
        list.remove(assetDataModel)
    }

    init {
        val repository = ExpensesRepository()
        repository.getMoreAboutExpense()
        saveAssetData(repository.getMoreAboutExpense())
    }

    fun saveAssetData(assetDataModel: ArrayList<MoreExpenseRecyclerViewItem.BodyMoreExpense>) =
        viewModelScope.launch {
            list.addAll(assetDataModel)
            _assetData.value = list
        }


}