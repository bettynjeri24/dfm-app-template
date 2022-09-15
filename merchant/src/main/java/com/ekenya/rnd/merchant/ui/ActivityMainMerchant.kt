package com.ekenya.rnd.merchant.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ekenya.rnd.merchant.databinding.ActivityMainMerchantBinding

class ActivityMainMerchant : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainMerchantBinding.inflate(layoutInflater).root)
    }
}