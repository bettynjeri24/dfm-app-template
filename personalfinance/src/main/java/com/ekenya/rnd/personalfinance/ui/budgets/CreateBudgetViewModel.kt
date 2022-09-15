package com.ekenya.rnd.personalfinance.ui.budgets

import androidx.lifecycle.ViewModel

class CreateBudgetViewModel : ViewModel() {
    // TODO: Implement the ViewModel
}




/*

  <com.github.mikephil.charting.charts.BarChart
                android:id="@+id/idBarChart"
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:layout_marginStart="@dimen/_8dp"
                android:layout_marginTop="@dimen/_8dp"
                android:layout_marginEnd="@dimen/_8dp"
                android:layout_marginBottom="@dimen/_8dp"
                android:visibility="gone"
                app:layout_constraintBottom_toBottomOf="@id/tv_expenditure_breakdown"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/clDuration" />


// variable for our bar chart
    private lateinit var barChart: BarChart

    // variable for our bar data set.
    private lateinit var barDataSet1: BarDataSet

    // array list for storing entries.
    private lateinit var barEntries: ArrayList<BarEntry>

    // creating a string array for displaying days.
    var days = arrayOf("S", "M", "T", "W", "T", "F", "S")


private fun setUpMpBarGraphs() {
     // initializing variable for bar chart.
     // initializing variable for bar chart.
     barChart = binding.idBarChart
     barDataSet1 = BarDataSet(getBarEntriesOne(), "")
     barDataSet1.color =
         resources
             .getColor(R.color.purple_200)
     val data = BarData(barDataSet1)

     barChart.data = data
     barChart.description.isEnabled = false

     val xAxis = barChart.xAxis
     xAxis.valueFormatter = IndexAxisValueFormatter(days)
     xAxis.setCenterAxisLabels(false)
     xAxis.position = XAxis.XAxisPosition.BOTTOM
     xAxis.granularity = 1f
     xAxis.isGranularityEnabled = true
     xAxis.isEnabled = true
     xAxis.spaceMax = 1f

     barChart.isDragEnabled = false
     barChart.isDragXEnabled = false
     barChart.isDragYEnabled = false
     barChart.isDoubleTapToZoomEnabled = false

     //barChart.setVisibleXRangeMaximum(3f)
     data.barWidth = 0.5f


     barChart.xAxis.axisMinimum = 0f

     barChart.axisLeft.setDrawGridLines(false)
     barChart.xAxis.setDrawGridLines(false)
     barChart.axisRight.setDrawGridLines(false)
     barChart.axisLeft.setDrawLabels(false)
     barChart.axisLeft.isEnabled = false
     barChart.axisRight.setDrawLabels(false)
     barChart.axisRight.isEnabled = false

     barChart.animate()
     barChart.invalidate()
 }

 // array list for first set
 private fun getBarEntriesOne(): ArrayList<BarEntry> {
     barEntries = ArrayList()
     barEntries.add(BarEntry(0f, 4f))
     barEntries.add(BarEntry(1f, 4f))
     barEntries.add(BarEntry(2f, 6f))
     barEntries.add(BarEntry(3f, 8f))
     barEntries.add(BarEntry(4f, 2f))
     barEntries.add(BarEntry(5f, 4f))
     barEntries.add(BarEntry(6f, 4f))
     return barEntries
 }*/