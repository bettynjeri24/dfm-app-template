package com.ekenya.rnd.merchant.ui.fragments.selected_ticket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekenya.rnd.merchant.R
import com.ekenya.rnd.merchant.databinding.FragmentSelectedEventBinding
import com.ekenya.rnd.merchant.databinding.FragmentSelectedTicketBinding
import com.ekenya.rnd.merchant.ui.fragments.events.Event


class SelectedTicketFragment : Fragment() {

    private lateinit var binding: FragmentSelectedTicketBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentSelectedTicketBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    private fun setUpUi() {
        bindEvent()
    }

    private fun bindEvent() {
        val event: Event? = arguments?.getParcelable("event")
        event?.let {
            binding.apply {
                imageViewTicket.setImageResource(event.image)
                textViewName.text = event.title
            }
        }
    }

}