package com.ekenya.rnd.wallet.data

import com.ekenya.rnd.wallet.R

data class DepositHistoryWalletModel(
    val titleDepositHistory: String,
    val imageDepositHistory: Int,
    val descDepositHistory: String,
    val dateTimeOfDepositHistory: String,
    val isDepositHistorySuccessful: Boolean = false,
) {
    companion object {
        fun getDepositHistoryModel(): ArrayList<DepositHistoryWalletModel> =
            arrayListOf(
                DepositHistoryWalletModel(
                    titleDepositHistory = "Successful Deposit",
                    imageDepositHistory = R.drawable.ic_succesful_deposit_wallet,
                    descDepositHistory = "You successfully deposited a cheque to your current A/c 12345*******678 of KES 100,000",
                    dateTimeOfDepositHistory = "28/01/2022 12:35pm",
                    isDepositHistorySuccessful = true
                ),
                DepositHistoryWalletModel(
                    titleDepositHistory = "Unsuccessful Deposit",
                    imageDepositHistory = R.drawable.ic_unsuccessful_deposit_wallet,
                    descDepositHistory = "Your cheque deposit to current A/c 12345**** ***678 of KES 100,000 was unsuccessful",
                    dateTimeOfDepositHistory = "28/01/2022 12:35pm"
                )
            )

    }


}