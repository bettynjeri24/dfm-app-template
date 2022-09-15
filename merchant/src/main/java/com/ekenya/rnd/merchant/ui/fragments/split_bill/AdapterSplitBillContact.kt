package com.ekenya.rnd.merchant.ui.fragments.split_bill

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.merchant.databinding.ItemSplitbillcontactMerchantBinding
import com.ekenya.rnd.merchant.domain.model.ContactModel
import com.ekenya.rnd.merchant.ui.fragments.direct_payment.FragmentDirectPayment
import com.ekenya.rnd.merchant.utils.getInitials
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class AdapterSplitBillContact(
    private val shouldSplitBill: MutableStateFlow<Boolean> = MutableStateFlow(false),
    private val sendCustomerAmount: (Int, Int) -> Boolean
) : ListAdapter<ContactModel, AdapterSplitBillContact.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemSplitbillcontactMerchantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {


        val contact = getItem(position)

        holder.itemSelectContactMerchantBinding.apply {

            // set the initials -> text view to the left of the itemview
            textViewNameInitials.text = contact.name.getInitials()

            // set name
            textViewName.text = contact.name

            // set phone number
            textViewPhone.text = contact.phone

            // divide the bills total with the number of payers
            textViewEqualAmount.text = (FragmentDirectPayment.amount / (1 + itemCount) ).toString()

            // listen to changes of the edit text
            // NB -> FEEDBACK loop design here
            editTextTextAmount.addTextChangedListener(object : TextWatcher {

                // keep track of the previous amount
                var previousAmount = 0

                // get previous amount and track it

                override fun beforeTextChanged(string: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val amount = if (!string.isNullOrEmpty()) string.toString().toInt() else 0
                    previousAmount = amount
                }

                // get new value and send the to the fragment for further processing
                override fun onTextChanged(
                    newValue: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // get new amount and convert to an Int
                    val newAmount = if (!newValue.isNullOrEmpty()) newValue.toString().toInt() else 0

                    // sendCustomerAmount returns true if new amount is in excess
                    val isExcess = sendCustomerAmount(previousAmount, newAmount)

                    // if the amount is in excess limit the edit text
                    if (isExcess){

                        // unregister the text change listener
                        editTextTextAmount.removeTextChangedListener(this)

                        // give edit text previous amount
                        editTextTextAmount.setText(previousAmount.toString(), TextView.BufferType.EDITABLE)

                        //placing cursor at the end of the text
                        editTextTextAmount.setSelection(editTextTextAmount.length())

                        //re register the text listener
                        editTextTextAmount.addTextChangedListener(this)

                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                    // do nothing
                }

            })

            CoroutineScope(Dispatchers.Main).launch {
                shouldSplitBill.collect { shouldSplit ->
                    when (shouldSplit) {
                        true -> {
                            editTextTextAmount.isVisible = false
                            textViewEqualAmount.isVisible = true
                        }
                        false -> {
                            editTextTextAmount.isVisible = true
                            textViewEqualAmount.isVisible = false
                        }
                    }
                }

            }

        }
    }

    class ViewHolder(val itemSelectContactMerchantBinding: ItemSplitbillcontactMerchantBinding) :
        RecyclerView.ViewHolder(itemSelectContactMerchantBinding.root)

    companion object {
        private const val TAG = "AdapterSplitBillContact"
    }

}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<ContactModel>() {

    override fun areItemsTheSame(oldItem: ContactModel, newItem: ContactModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ContactModel, newItem: ContactModel): Boolean {
        return oldItem == newItem
    }

}
