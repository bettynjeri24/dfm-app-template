package com.ekenya.rnd.wallet.ui.adapters.listeners

import android.view.View
import com.ekenya.rnd.wallet.data.BillsWalletModel
import com.ekenya.rnd.wallet.data.ContactListWalletModel
import com.ekenya.rnd.wallet.data.MyAccountWalletModel

interface OnContactItemClickedListener {
    fun onClickedItem(view: View, model: ContactListWalletModel)
}