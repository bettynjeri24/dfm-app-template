package com.ekenya.rnd.wallet.ui.adapters.listeners

import android.view.View
import com.ekenya.rnd.wallet.data.BillsWalletModel

interface OnBillItemClickedListener {
    fun onBillsWalletModelClicked(view: View, model: BillsWalletModel)
}