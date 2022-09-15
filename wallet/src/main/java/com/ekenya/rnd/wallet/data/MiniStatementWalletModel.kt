package com.ekenya.rnd.wallet.data

import com.ekenya.rnd.wallet.R

data class MiniStatementWalletModel(
    val titleMiniStatement: String,
    val imageMiniStatement: Int,
    val amountMiniStatement: String,
    val dateTimeOfMiniStatement: String,
    val isMiniStatementCashOut: Boolean = false
) {
    companion object {
        fun getMiniStatementModel(): ArrayList<MiniStatementWalletModel> =
            arrayListOf(
                MiniStatementWalletModel(
                    titleMiniStatement = "Send to Mpesa",
                    imageMiniStatement = R.drawable.ic_unsuccessful_deposit_wallet,
                    amountMiniStatement = "- Kes 2,000.00",
                    dateTimeOfMiniStatement = "28/01/2022 12:35pm"
                ),
                MiniStatementWalletModel(
                    titleMiniStatement = "Loan Repayment",
                    imageMiniStatement = R.drawable.ic_unsuccessful_deposit_wallet,
                    amountMiniStatement = "- Kes 4,400.00",
                    dateTimeOfMiniStatement = "28/01/2022 12:35pm"
                ),
                MiniStatementWalletModel(
                    titleMiniStatement = "Zuku Bill Payment",
                    imageMiniStatement = R.drawable.ic_unsuccessful_deposit_wallet,
                    amountMiniStatement = "- Kes 4,400.00",
                    dateTimeOfMiniStatement = "28/01/2022 12:35pm"
                ),
                MiniStatementWalletModel(
                    titleMiniStatement = "Requested Funds",
                    imageMiniStatement = R.drawable.ic_succesful_deposit_wallet,
                    amountMiniStatement = "+ Kes 8,000.00",
                    dateTimeOfMiniStatement = "28/01/2022 12:35pm",
                    isMiniStatementCashOut = true
                )

            )

    }
}