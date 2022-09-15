package com.ekenya.rnd.mycards.ui.dialog.alertDialog

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.ekenya.rnd.mycards.ui.dialog.alertDialog.dialog_change_pin.DialogChangePin
import com.ekenya.rnd.mycards.ui.dialog.alertDialog.dialog_confirm_details.DialogDetailsCards

/*
 *  Dialog
 */

// Make it available to activities
inline fun Activity.showAlertDialog(func: DialogDetailsCards.() -> Unit): AlertDialog =
    DialogDetailsCards(this).apply {
        func()
    }.create()

// Make it available to fragments
inline fun Fragment.showAlertDialog(func: DialogDetailsCards.() -> Unit): AlertDialog =
    DialogDetailsCards(requireContext()).apply {
        func()
    }.create()

inline fun Fragment.showChangePinDialog(func: DialogChangePin.() -> Unit): AlertDialog =
    DialogChangePin(requireContext()).apply {
        func()
    }.create()


