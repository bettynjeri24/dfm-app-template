package com.ekenya.rnd.personalfinance.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {
    private val mText: MutableLiveData<String?> = MutableLiveData()
    val text: LiveData<String?>
        get() = mText

    init {
        mText.value = "This is home fragment"
    }


}


/**
 *
val dataSet = PieDataSet(yValues, "")

dataSet.sliceSpace = 6f
dataSet.selectionShift = 6f
dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
pieChart.animateY(500)
val l: Legend = pieChart.getLegend()
val data = PieData(dataSet)
l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
l.orientation = Legend.LegendOrientation.VERTICAL
l.setDrawInside(false)
l.form = Legend.LegendForm.CIRCLE
l.xEntrySpace = 10f
l.yEntrySpace = 0f
l.yOffset = 30f
l.isWordWrapEnabled = true
l.setDrawInside(false)
l.calculatedLineSizes
data.setValueTextSize(40f)
data.setValueTextColor(Color.BLACK)
pieChart.setData(data)
pieChart.highlightValues(null)
pieChart.invalidate()
 */