package com.ekenya.rnd.merchant.ui.dialog.datePickerDialog

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import java.util.*

class DatePickerDialogUiMerchant(val fragment: Fragment) : DialogFragment(), OnDateSetListener {

    internal interface OnTagListener {
        fun onDateAdjusted(year: Int, month: Int, day: Int,tag: String?)
    }

    private val tagListener: OnTagListener = fragment as OnTagListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]
        return DatePickerDialog(requireActivity(), this, year,  month, day)
    }

    override fun onDateSet(p0: DatePicker?,year: Int, month: Int, day: Int) {
        tagListener.onDateAdjusted(year,month,day,tag)
    }


}