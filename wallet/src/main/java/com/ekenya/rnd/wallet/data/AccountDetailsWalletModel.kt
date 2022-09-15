package com.ekenya.rnd.wallet.data

import com.ekenya.rnd.wallet.R
import kotlinx.parcelize.RawValue


data class AccountDetailsWalletModel(
    val titleAccountDetails: String,
    val imageAccountDetails: Int
) {
    companion object {
        fun getAllAccountDetailsModel(): ArrayList<AccountDetailsWalletModel> =
            arrayListOf(
                AccountDetailsWalletModel(
                    titleAccountDetails = "Withdraw",
                    imageAccountDetails = R.drawable.ic_withdraw_wallet,

                    ),
                AccountDetailsWalletModel(
                    titleAccountDetails = "Mini\nStatements",
                    imageAccountDetails = R.drawable.ic_mini_statements_wallet,
                ),
                AccountDetailsWalletModel(
                    titleAccountDetails = "Full\nStatements",
                    imageAccountDetails = R.drawable.ic_mini_statements_wallet,
                ),
                AccountDetailsWalletModel(
                    titleAccountDetails = "Cheque Book\nRequests",
                    imageAccountDetails = R.drawable.ic_cheque_book_requests_wallet,
                ),
                AccountDetailsWalletModel(
                    titleAccountDetails = "Notification\nSettings",
                    imageAccountDetails = R.drawable.ic_notification_settings_wallet,
                ),
                AccountDetailsWalletModel(
                    titleAccountDetails = "Unlink\nAccount",
                    imageAccountDetails = R.drawable.ic_unlink_account_wallet,
                )

            )

        fun getLinkAccountAccountDetailsModel(): ArrayList<AccountDetailsWalletModel> =
            arrayListOf(
                AccountDetailsWalletModel(
                    titleAccountDetails = "Link Account",
                    imageAccountDetails = R.drawable.ic_link_account_wallet,

                    ))
    }
}