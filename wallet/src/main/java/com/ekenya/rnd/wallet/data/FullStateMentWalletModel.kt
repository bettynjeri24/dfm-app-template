package com.ekenya.rnd.wallet.data

import com.ekenya.rnd.wallet.R

data class FullStatementWalletModel(
    val titleFullStatement: String,
    val imageIconFullStatement: Int,
    val imageDownloadFullStatement: Int,
    val isFullStatementDownloaded: Boolean = false
) {
    companion object {
        fun getFullStatementModel(): ArrayList<FullStatementWalletModel> =
            arrayListOf(
                FullStatementWalletModel(
                    titleFullStatement = "01 Jan 2022- (free).pdf",
                    imageIconFullStatement = R.drawable.ic_mini_statements_wallet,
                    imageDownloadFullStatement = R.drawable.ic_download_fullstatement_wallet,

                    ),
                FullStatementWalletModel(
                    titleFullStatement = "01 Jan 2021.pdf",
                    imageIconFullStatement = R.drawable.ic_mini_statements_wallet,
                    imageDownloadFullStatement = R.drawable.ic_download_fullstatement_wallet,
                    isFullStatementDownloaded = true
                ),
                FullStatementWalletModel(
                    titleFullStatement = "01 Jan 2021.pdf",
                    imageIconFullStatement = R.drawable.ic_mini_statements_wallet,
                    imageDownloadFullStatement = R.drawable.ic_download_fullstatement_wallet,
                )

            )
    }
}