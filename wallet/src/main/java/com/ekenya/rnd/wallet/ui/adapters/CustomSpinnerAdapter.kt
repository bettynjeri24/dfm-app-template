package com.ekenya.rnd.wallet.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.ekenya.rnd.wallet.data.ServiceProviderWalletModel
import com.ekenya.rnd.wallet.databinding.ItemServiceProviderCustomSpinnerWalletBinding


class CustomArrayAdapter(context: Context, var dataSource: List<ServiceProviderWalletModel>) :
    ArrayAdapter<ServiceProviderWalletModel>(context, 0, dataSource) {
    private lateinit var binding: ItemServiceProviderCustomSpinnerWalletBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        if (convertView == null) {
            binding =
                ItemServiceProviderCustomSpinnerWalletBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            binding.tvServiceProvider.text = dataSource[position].titleServiceProvider
            binding.ivServiceProvider.setBackgroundResource(dataSource[position].imageServiceProvider)

        }
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getDropDownView(position, convertView, parent)
        /* val txt = TextView(context)
         txt.setPadding(16, 16, 16, 16)
         txt.textSize = 18f
         txt.gravity = Gravity.CENTER_VERTICAL
         txt.setText(dataSource[position].titleServiceProvider)
         txt.setTextColor(Color.parseColor("#000000"))
         return txt*/
        if (convertView == null) {
            binding =
                ItemServiceProviderCustomSpinnerWalletBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            binding.tvServiceProvider.text = dataSource[position].titleServiceProvider
            binding.ivServiceProvider.setBackgroundResource(dataSource[position].imageServiceProvider)

        }
        return binding.root
    }

/*    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): ServiceProviderWalletModel? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }*/
}

