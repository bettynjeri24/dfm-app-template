package com.ekenya.rnd.mycards.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ekenya.rnd.mycards.R

//Extension load an image into an imageview
fun ImageView.load(resource: Any) {
    when (resource) {
        is String, is Int, is Drawable -> {
            Glide.with(this.context)
                .load(resource)
                .into(this)
        }
        else -> {
            throw IllegalArgumentException("Cannot load resource")
        }
    }
}

//Toast a message
fun Fragment.toast(string: String) {
    Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
}


//SetAdapter on a spinner
fun AutoCompleteTextView.setUpSpinner(arrayResource: Int, onItemClick : (parent: AdapterView<*>?,
                                                                         view: View?,
                                                                         position: Int,
                                                                         id: Long)  -> Unit) {
    val providers = resources.getStringArray(arrayResource)
    val providerAdapter = ArrayAdapter(
        context,
        R.layout.autocomplete_list_item_cards,
        providers
    )
    setAdapter(providerAdapter)
    setOnFocusChangeListener { v, hasFocus ->
    }
    setOnItemClickListener { parent, view, position, id ->
        onItemClick(parent,view,position,id )
    }
}

