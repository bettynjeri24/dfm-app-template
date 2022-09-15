package com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.wallet.data.ContactListWalletModel
import com.ekenya.rnd.wallet.databinding.ItemSplitContactWalletBinding
import com.ekenya.rnd.wallet.utils.getFirstLetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

private val hash: HashMap<Int, Int> = HashMap()

class AdapterSplitMobileMoneyContact(
    private val equalAmount: MutableStateFlow<Int>,
    private val adapterContentChanged: AdapterContentChanged
) : ListAdapter<ContactListWalletModel, AdapterSplitMobileMoneyContact.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemSplitContactWalletBinding.inflate(
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
        val ownViewHolderTextChanged = object : OwnViewHolderTextChanged {
            override fun onTextChanged(position: Int, newValue: Int) {
                Timber.e("NEW VALUE NEW VALUE NEW VALUE NEW VALUE $newValue")
                hash[position] = newValue
                adapterContentChanged.valuesChanged(getTotalAmount())
            }
        }

        holder.bind(position, ownViewHolderTextChanged)

        holder.binding.apply {
            if (contact.titleContactName.isEmpty()) {
                tvAddBeneficiary.visibility = View.GONE
                iVAddBeneficiary.visibility = View.VISIBLE
                textViewName.text = contact.phoneContact
                textViewPhone.text = contact.phoneContact
            } else {
                tvAddBeneficiary.text = contact.titleContactName.getFirstLetter()
                textViewName.text = contact.titleContactName
                textViewPhone.text = contact.phoneContact
            }
            // set the initials -> text view to the left of the itemview

            // set name

            // divide the bills total with the number of payers
            CoroutineScope(Dispatchers.Main).launch {
                equalAmount.collect { newAmount ->
                    editTextTextAmount.setText(newAmount.toString())
                }
            }
        }
    }

    class ViewHolder(val binding: ItemSplitContactWalletBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            position: Int,
            listener: OwnViewHolderTextChanged
        ) {
            binding.editTextTextAmount.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    // Get EditText content
                    val newValue = getNumber()
                    // Call method from interface
                    listener.onTextChanged(position = position, newValue = newValue)
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }

        private fun getNumber(): Int = try {
            binding.editTextTextAmount.text.toString().toInt()
        } catch (e: Exception) {
            1
        }
    }

    private fun getTotalAmount(): Int {
        var totalSum = 0
        for ((key, value) in hash) {
            Timber.e("$key = $value")
            totalSum += value
        }
        return totalSum
    }
}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<ContactListWalletModel>() {

    override fun areItemsTheSame(
        oldItem: ContactListWalletModel,
        newItem: ContactListWalletModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ContactListWalletModel,
        newItem: ContactListWalletModel
    ): Boolean {
        return oldItem == newItem
    }
}

// adapter na fragment
interface AdapterContentChanged {
    fun valuesChanged(sum: Int)
}

// adatper nad viewholder
interface OwnViewHolderTextChanged {
    fun onTextChanged(position: Int, newValue: Int)
}
