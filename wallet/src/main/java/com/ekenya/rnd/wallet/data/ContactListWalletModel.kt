package com.ekenya.rnd.wallet.data

import android.os.Parcelable
import com.ekenya.rnd.wallet.R
import kotlinx.parcelize.Parcelize


/*
 * Model the contacts
 */

@Parcelize
data class ContactListWalletModel(
    var titleContactName: String,
    var phoneContact: String,
    val initialsContact: String = "",
    var amountToSend: Int = 0
) : Parcelable {
    companion object {
        fun getContactModel(): ArrayList<ContactListWalletModel> =
            arrayListOf(
                ContactListWalletModel(
                    titleContactName = "Myself",
                    phoneContact = "+25412345678",
                    initialsContact = "M"
                ),
                ContactListWalletModel(
                    titleContactName = "Allan Doe",
                    phoneContact = "+25412345678",
                    initialsContact = "AM"
                )
            )

        fun getCAddBeneficiaryModel(): ArrayList<ContactListWalletModel> =
            arrayListOf(
                ContactListWalletModel(
                    titleContactName = "Moses Kariuki",
                    phoneContact = "+25412345678",
                    initialsContact = "M"
                ),
                ContactListWalletModel(
                    titleContactName = "Allan Doe",
                    phoneContact = "+25412345678",
                    initialsContact = "AM"
                )
            )
    }
}