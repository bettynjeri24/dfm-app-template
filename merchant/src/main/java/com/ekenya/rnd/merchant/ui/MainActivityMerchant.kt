package com.ekenya.rnd.merchant.ui

import android.os.Bundle
import com.ekenya.rnd.merchant.R
import dagger.android.DaggerActivity

class MainActivityMerchant : DaggerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}