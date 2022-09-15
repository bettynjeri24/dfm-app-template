package com.ekenya.rnd.mycards.ui.dialog.alertDialog.dialog_change_pin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.ekenya.rnd.mycards.R
import com.ekenya.rnd.mycards.ui.dialog.alertDialog.base.BaseDialogHelperMerchant


// Structure your dialog here

class DialogChangePin(val context: Context) : BaseDialogHelperMerchant() {

    //  dialog view
    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_change_pin_cards, null)
    }

    //dialog builder
    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    //  confirm button
    private val confirmButton by lazy {
        dialogView.findViewById<Button>(R.id.button_confirm)
    }

    //  cancel button
    private val cancelButton by lazy {
        dialogView.findViewById<Button>(R.id.button_cancel)
    }

    //  cancel button ClickListener with listener
    fun cancelButtonClickListener(func: (() -> Unit)? = null) =
        with(cancelButton) {
            setClickListenerToDialogIcon(func)
        }

    //  confirm button ClickListener with listener
    fun confirmButtonClickListener(func: (() -> Unit)? = null) =
        with(confirmButton) {
            setClickListenerToDialogIcon(func)
        }

    //  view click listener as extension function
    private fun View.setClickListenerToDialogIcon(func: (() -> Unit)?) =
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }
}