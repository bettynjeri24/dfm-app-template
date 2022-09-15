package com.ekenya.rnd.mycards.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ekenya.rnd.mycards.databinding.ActivityMainCardsBinding

class ActivityMainMyCards : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainCardsBinding.inflate(layoutInflater).root)
    }

}