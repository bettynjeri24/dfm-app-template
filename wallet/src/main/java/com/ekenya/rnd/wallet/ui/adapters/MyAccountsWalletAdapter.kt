package com.ekenya.rnd.wallet.ui.adapters

import android.text.method.HideReturnsTransformationMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.MyAccountWalletModel
import com.ekenya.rnd.wallet.databinding.ItemsMyAccountWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.listeners.OnAccountsItemClickedListener
import com.ekenya.rnd.wallet.utils.maskTextInTextView


class MyAccountsWalletAdapter(
    private val model: ArrayList<MyAccountWalletModel>,
    private val listener: OnAccountsItemClickedListener
) : ListAdapter<MyAccountWalletModel, MyAccountsWalletAdapter.ViewHolder>(DIFF_UTIL) {
    private var isAvailableBalanceVisible = true
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemsMyAccountWalletBinding.inflate(
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

        /* bind card details */
        holder.binding.apply {
            tvAccountType.text = model[position].titleMyAccount

            cvAccountType.apply {

                setBackgroundColor(
                    model[position].colorMyAccount
                )
            }
            clMyAccountBg.setBackgroundResource(model[position].imageBgMyAccount)
            cvAllMyAccounts.setOnClickListener {
                listener.onClickedItem(it, model[position])
                //it.findNavController().navigate(R.id.moreAboutExpensesFragment)
            }
            ivValueVisibility.setOnClickListener {
                if (isAvailableBalanceVisible) {
                    tvAvailableBalanceAmount.text =
                        model[position].amountMyAccount
                    tvAvailableBalanceAmount.transformationMethod = maskTextInTextView
                    ivValueVisibility.setImageResource(R.drawable.ic_outline_visibility_on_wallet)
                    isAvailableBalanceVisible = !isAvailableBalanceVisible
                } else {
                    tvAvailableBalanceAmount.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    tvAvailableBalanceAmount.text =
                        model[position].amountMyAccount
                    ivValueVisibility.setImageResource(R.drawable.ic_outline_visibility_off_wallet)
                    isAvailableBalanceVisible = !isAvailableBalanceVisible
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return model.size
    }

    inner class ViewHolder(val binding: ItemsMyAccountWalletBinding) :
        RecyclerView.ViewHolder(binding.root)

}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<MyAccountWalletModel>() {
    override fun areItemsTheSame(
        oldItem: MyAccountWalletModel,
        newItem: MyAccountWalletModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MyAccountWalletModel,
        newItem: MyAccountWalletModel
    ): Boolean {
        return oldItem == newItem
    }

}
