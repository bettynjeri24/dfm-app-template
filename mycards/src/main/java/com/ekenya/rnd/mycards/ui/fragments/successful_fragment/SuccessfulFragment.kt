package com.ekenya.rnd.mycards.ui.fragments.successful_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.common.dialogs.base.adapter_detail.DetailDialogAdapter
import com.ekenya.rnd.mycards.R
import com.ekenya.rnd.mycards.databinding.FragmentSucessfulCardsBinding
import com.ekenya.rnd.mycards.ui.fragments.home.MyCardsHomeFragment

import dagger.android.support.DaggerFragment

class SuccessfulFragment : DaggerFragment() {

    private lateinit var binding: FragmentSucessfulCardsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSucessfulCardsBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    // set up UI
    private fun setUpUI() {

        binding.apply {

            recyclerViewDetails.apply {
                val dialogAdapter = DetailDialogAdapter()
                val linearLayoutManager = LinearLayoutManager(context)
                layoutManager = linearLayoutManager
                dialogAdapter.submitList(MyCardsHomeFragment.details)
                adapter = dialogAdapter
            }

            buttonContinue.setOnClickListener {
                findNavController().navigate(R.id.action_successful_to_home)
            }

            textViewTitle.text = MyCardsHomeFragment.title
            textViewSubTitle.text = MyCardsHomeFragment.subtitle
            textViewCardTitle.text = MyCardsHomeFragment.cardTitle
            textViewCardContent.text = MyCardsHomeFragment.cardText

        }

    }


}
