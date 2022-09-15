package com.ekenya.rnd.wallet.ui.adapters.listeners

import android.view.View
import com.ekenya.rnd.wallet.data.AccountDetailsWalletModel
import com.ekenya.rnd.wallet.data.BillsWalletModel
import com.ekenya.rnd.wallet.data.MyAccountWalletModel

interface OnAccountsDetailsItemClickedListener {
    fun onClickedItem(view: View, model: AccountDetailsWalletModel)
}