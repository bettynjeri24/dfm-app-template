package com.ekenya.rnd.wallet.ui.home

import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import javax.inject.Inject
import androidx.lifecycle.ViewModelProvider
import com.ekenya.rnd.wallet.ui.home.HomeViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class HomeViewModel @Inject constructor() : ViewModel() {
    private val mText: MutableLiveData<String?>
    val text: LiveData<String?>
        get() = mText

    init {
        mText = MutableLiveData()
        mText.value = "This is home fragment"
    }
}