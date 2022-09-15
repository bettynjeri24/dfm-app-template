package com.ekenya.rnd.wallet.data

import android.graphics.Color
import android.os.Parcelable
import com.ekenya.rnd.wallet.R
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class MyAccountWalletModel(
    val titleMyAccount: String,
    val imageBgMyAccount: Int,
    val amountMyAccount: String,
    val colorMyAccount: Int,
    val accountDetailsWalletModel: @RawValue ArrayList<AccountDetailsWalletModel>
) : Parcelable {
    companion object {
        fun getMyLinkedAccountWalletModel(): ArrayList<MyAccountWalletModel> =
            arrayListOf(
                MyAccountWalletModel(
                    titleMyAccount = "Premier current account - 4001234567890",
                    imageBgMyAccount = R.drawable.my_account_1_wallet,
                    amountMyAccount = "22,000.00",
                    colorMyAccount = Color.parseColor("#F69414"),
                    accountDetailsWalletModel = AccountDetailsWalletModel.getAllAccountDetailsModel()

                ), MyAccountWalletModel(
                    titleMyAccount = "Savings account - 4001234567890",
                    imageBgMyAccount = R.drawable.my_account_2_wallet,
                    amountMyAccount = "22,000.00",
                    colorMyAccount = Color.parseColor("#1AA7E1"),
                    accountDetailsWalletModel = AccountDetailsWalletModel.getAllAccountDetailsModel()
                )
            )

        fun getMyUnLinkedAccountWalletModel(): ArrayList<MyAccountWalletModel> =
            arrayListOf(
                MyAccountWalletModel(
                    titleMyAccount = "Premium banking",
                    imageBgMyAccount = R.drawable.my_account_1_wallet,
                    amountMyAccount = "40XXXXXXX7890",
                    colorMyAccount = Color.parseColor("#F69414"),
                    accountDetailsWalletModel = AccountDetailsWalletModel.getLinkAccountAccountDetailsModel()
                )
            )

        fun getMyDormantAccountWalletModel(): ArrayList<MyAccountWalletModel> =
            arrayListOf(
                MyAccountWalletModel(
                    titleMyAccount = "Premium banking",
                    imageBgMyAccount = R.drawable.my_account_1_wallet,
                    amountMyAccount = "40XXXXXXX7890",
                    colorMyAccount = Color.parseColor("#F69414"),
                    accountDetailsWalletModel = AccountDetailsWalletModel.getLinkAccountAccountDetailsModel()
                )
            )
    }
}