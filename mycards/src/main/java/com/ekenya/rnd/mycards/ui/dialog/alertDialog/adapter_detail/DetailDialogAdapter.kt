package com.ekenya.rnd.mycards.ui.dialog.alertDialog.adapter_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.mycards.databinding.ItemDialogCardsBinding
import com.ekenya.rnd.mycards.ui.dialog.alertDialog.model.DialogDetail


// populates the details in the dialog recyclerview

// NOTE! This is also re used in the success fragment

class DetailDialogAdapter() : ListAdapter<DialogDetail,DetailDialogAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDialogCardsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dialogDetail = getItem(position)
        holder.itemDialogCardsBinding.apply {
            textViewlabel.text = dialogDetail.label
            textViewContent.text = dialogDetail.content
        }
    }


    class ViewHolder(val itemDialogCardsBinding: ItemDialogCardsBinding) : RecyclerView.ViewHolder(itemDialogCardsBinding.root)


}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<DialogDetail>() {
    override fun areItemsTheSame(oldItem: DialogDetail, newItem: DialogDetail): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DialogDetail, newItem: DialogDetail): Boolean {
        return oldItem == newItem
    }

}