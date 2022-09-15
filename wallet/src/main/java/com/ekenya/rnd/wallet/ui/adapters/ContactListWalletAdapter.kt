package com.ekenya.rnd.wallet.ui.adapters

import android.database.Cursor
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.ekenya.rnd.wallet.data.ContactListWalletModel
import com.ekenya.rnd.wallet.databinding.ItemContactListLayoutWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.listeners.OnContactItemClickedListener
import com.ekenya.rnd.wallet.utils.getFirstLetter

/**
 * Displays a list of contacts fro which to choose from
 */
class ContactListWalletAdapter(private val listener: OnContactItemClickedListener) :
    BaseCursorAdapter<ContactListWalletAdapter.ViewHolder>(null) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactListLayoutWalletBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, cursor: Cursor?) {

        holder.itemSelectContactMerchantBinding.apply {

            val contact = getContact(cursor)

            //bind contact details to recyclerview

            contact.apply {
//                val imageBytes = Base64.decode(initials, 0);
//                val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size);
                iVContactList.text = initialsContact;
                tVContactListDesc.text = phoneContact;
                tVTitleContactList.text = titleContactName

                mcVRoot.setOnClickListener {
                    listener.onClickedItem(it, model = contact)
                }
            }


        }
    }

    private fun getContact(cursor: Cursor?): ContactListWalletModel {

        //load name from cursor
        val mColumnIndexName = cursor!!.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
        val contactName = cursor.getString(mColumnIndexName)

        //get initials from name
        val initials = contactName.getFirstLetter()

        //load phone number
        val phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        val phone = cursor.getString(phoneIndex);

        //build contact model object
        return ContactListWalletModel(contactName, phone, initials)
    }

    class ViewHolder(val itemSelectContactMerchantBinding: ItemContactListLayoutWalletBinding) :
        RecyclerView.ViewHolder(itemSelectContactMerchantBinding.root)

}

