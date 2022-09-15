package com.ekenya.rnd.personalfinance.ui.budgets.managebudget

import com.github.eclectics.charting.components.AxisBase
import com.github.eclectics.charting.formatter.IAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

//class MonthValueFormatter : ValueFormatter() {
class MonthValueFormatter : IAxisValueFormatter {

    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return when (value) {
            0.0f -> "January"
            1.0f -> "February"
            2.0f -> "Mars"
            3.0f -> "April"
            4.0f -> "May"
            5.0f -> "June"
            6.0f -> "July"
            7.0f -> "August"
            8.0f -> "September"
            9.0f -> "October"
            10.0f -> "November"
            11.0f -> "December"
            else -> throw IllegalArgumentException("$value is not a valid month")
        }
    }


}


class AmountValueFormatter : IAxisValueFormatter {

    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        val yAxisLabel: ArrayList<Int> = ArrayList()
        yAxisLabel.add(10)
        yAxisLabel.add(10)
        yAxisLabel.add(20)
        yAxisLabel.add(30)
        yAxisLabel.add(40)
        yAxisLabel.add(50)
        yAxisLabel.add(60)

        return yAxisLabel.toString()
    }


}


class DayValueFormatter : IAxisValueFormatter {

    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        // Convert float value to date string
        // Convert from days back to milliseconds to format time  to show to the user
        // Convert float value to date string
        // Convert from days back to milliseconds to format time  to show to the user
        val emissionsMilliSince1970Time: Long = TimeUnit.DAYS.toMillis(value.toLong())
        // Show time in local version
        // Show time in local version
        val timeMilliseconds = Date(emissionsMilliSince1970Time)
        val dateTimeFormat = SimpleDateFormat("dd")

        return "${dateTimeFormat.format(timeMilliseconds)}K"
    }
}
