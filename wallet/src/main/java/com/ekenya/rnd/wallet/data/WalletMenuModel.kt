package com.ekenya.rnd.wallet.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ekenya.rnd.wallet.R


data class WalletMenuModel(
    var menuId: Int,
    var menuName: String = "",
    var menuImg: Int = 0,
) {
    companion object {
        fun getWalletMenuModelModel() = listOf(
            WalletMenuModel(1, "My Accounts", R.drawable.ic_bill_payment),
            WalletMenuModel(2, "Funds Transfer", R.drawable.ic_bill_payment),
            WalletMenuModel(3, "Bills", R.drawable.ic_bill_payment),
            WalletMenuModel(4, "Buy AirTime", R.drawable.ic_bill_payment),
            WalletMenuModel(5, "Deposit", R.drawable.ic_bill_payment),
            WalletMenuModel(6, "Withdraw", R.drawable.ic_bill_payment),
        )


    }
}