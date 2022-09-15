package com.ekenya.rnd.wallet.data

import android.os.Parcelable
import com.ekenya.rnd.wallet.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class BillsWalletModel(
    val titleBills: String,
    val imageBills: Int,
    val descBills: String,
    val isBillPaid: Boolean = false
) : Parcelable {
    companion object {
        fun getAllBillsModel(): ArrayList<BillsWalletModel> =
            arrayListOf(
                BillsWalletModel(
                    titleBills = "Kplc Post-paid",
                    imageBills = R.drawable.kplc_wallet,
                    descBills = "Pay for your postpaid electricity bill"
                ),
                BillsWalletModel(
                    titleBills = "Kplc Pre-paid",
                    imageBills = R.drawable.kplc_wallet,
                    descBills = "Pay for your pre-paid electricity bill"
                ),
                BillsWalletModel(
                    titleBills = "Zuku Internet",
                    imageBills = R.drawable.zuku_wallet,
                    descBills = "Pay for your Zuku internet bill"
                ),
                BillsWalletModel(
                    titleBills = "Nairobi water",
                    imageBills = R.drawable.nairobiwater_wallet,
                    descBills = "Pay for your water bills"
                ),
                BillsWalletModel(
                    titleBills = "NHIF",
                    imageBills = R.drawable.nhif_wallet,
                    descBills = "Pay for your NHIF bills"
                )

            )

        fun getFrequentBillsModel(): ArrayList<BillsWalletModel> =
            arrayListOf(

                BillsWalletModel(
                    titleBills = "Zuku Internet",
                    imageBills = R.drawable.zuku_wallet,
                    descBills = "Pay for your Zuku internet bill",
                    isBillPaid = false
                ),

                BillsWalletModel(
                    titleBills = "Kplc Pre-paid",
                    imageBills = R.drawable.kplc_wallet,
                    descBills = "Pay for your pre-paid electricity bill",
                    isBillPaid = true
                )


            )
    }
}