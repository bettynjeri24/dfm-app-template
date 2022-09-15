package com.ekenya.rnd.personalfinance.utils

import android.app.Activity
import android.graphics.Color
import android.view.View
import com.ekenya.rnd.personalfinance.R
import com.google.android.material.appbar.MaterialToolbar


fun MaterialToolbar.setBackButton(title: Int, context: Activity,color: Int?= Color.WHITE) {
    this.setNavigationIcon(R.drawable.back_personal_finance_pf)
    this.setTitle(title)
    this.setTitleTextColor(color!!)
    this.setNavigationOnClickListener { view1: View? -> context.onBackPressed() }
}
