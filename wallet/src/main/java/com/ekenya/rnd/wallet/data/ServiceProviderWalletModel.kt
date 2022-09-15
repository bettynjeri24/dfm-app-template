package com.ekenya.rnd.wallet.data

import com.ekenya.rnd.wallet.R

data class ServiceProviderWalletModel(
    val titleServiceProvider: String,
    val imageServiceProvider: Int
) {
    companion object {
        fun getAllServiceProviderModel(): ArrayList<ServiceProviderWalletModel> =
            arrayListOf(
                ServiceProviderWalletModel(
                    titleServiceProvider = "Safaricom",
                    imageServiceProvider = R.drawable.safaricom_wallet,

                    ),
                ServiceProviderWalletModel(
                    titleServiceProvider = "Airtel",
                    imageServiceProvider = R.drawable.airtel_wallet,
                ),
                ServiceProviderWalletModel(
                    titleServiceProvider = "T-kash",
                    imageServiceProvider = R.drawable.telkom_wallet,
                )

            )
    }

 /*   override fun toString(): String {
        return titleServiceProvider
    }*/
}