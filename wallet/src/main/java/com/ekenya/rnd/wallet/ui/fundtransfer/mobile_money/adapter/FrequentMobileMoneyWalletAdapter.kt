package com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.adapter

import android.database.Cursor
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.ContactListWalletModel
import com.ekenya.rnd.wallet.databinding.ItemSelectcontactWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.BaseCursorAdapter
import com.ekenya.rnd.wallet.utils.getFirstLetter

class FrequentMobileMoneyWalletAdapter(
    private val onContactSelected: (ContactListWalletModel) -> Boolean,
    private val contains: (ContactListWalletModel) -> Boolean
) : BaseCursorAdapter<FrequentMobileMoneyWalletAdapter.ViewHolder>(null) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSelectcontactWalletBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, cursor: Cursor?) {
        holder.itemSelectContactMerchantBinding.apply {
            val contact = getContact(cursor)

            // bind contact details to recyclerview
            contact.apply {
                textViewNameInitials.text = initialsContact
                textViewPhone.text = phoneContact
                textViewName.text = titleContactName
            }

            // assign drawable based on the existence of the object in the set
            if (!contains(contact)) { // perform check using higher order function defined in UI
                // object exists
                imageViewSelectedIndicate.setImageResource(R.drawable.ic_notselected_wallet)
            } else {
                // object doesn't exist
                imageViewSelectedIndicate.setImageResource(R.drawable.ic_selected_wallet)
            }

            // itemview clicked
            root.setOnClickListener {
                // maintain a set of selected contacts in UI using a higher order function
                if (!onContactSelected.invoke(contact)) {
                    // object exists
                    imageViewSelectedIndicate.setImageResource(R.drawable.ic_notselected_wallet)
                } else {
                    // object doesn't exist
                    imageViewSelectedIndicate.setImageResource(R.drawable.ic_selected_wallet)
                }
            }
        }
    }

    private fun getContact(cursor: Cursor?): ContactListWalletModel {
        // load name from cursor
        val mColumnIndexName = cursor!!.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
        val contactName = cursor.getString(mColumnIndexName)

        // get initials from name
        val initials = contactName.getFirstLetter()

        // load phone number
        val phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        val phone = cursor.getString(phoneIndex)

        // build contact model object
        return ContactListWalletModel(contactName, phone, initials)
    }

    class ViewHolder(val itemSelectContactMerchantBinding: ItemSelectcontactWalletBinding) :
        RecyclerView.ViewHolder(itemSelectContactMerchantBinding.root)
}
