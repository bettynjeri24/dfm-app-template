package com.ekenya.rnd.mycards.ui.fragments.mini_statement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.ekenya.rnd.common.form_validation.ui_extensions.formatAsHiddenCardNumber
import com.ekenya.rnd.mycards.databinding.FragmentMiniStatementCardsBinding
import com.ekenya.rnd.mycards.ui.fragments.home.MyCardsHomeFragment
import com.ekenya.rnd.mycards.utils.MyCardsListUtils
import com.ekenya.rnd.mycards.utils.Statement
import dagger.android.support.DaggerFragment


class FragmentMiniStatement : DaggerFragment() {

    private lateinit var binding: FragmentMiniStatementCardsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMiniStatementCardsBinding.inflate(inflater, container, false)
        setUpUi()
        return binding.root
    }


    private fun setUpUi() {
        setUpTransactionsAdapter(MyCardsListUtils.statements)
        setUpBindings()
    }

    private fun setUpBindings() {
        binding.apply {
            //Navigate back on the toolbar
            toolbar.setNavigationOnClickListener {
                NavHostFragment.findNavController(this@FragmentMiniStatement).navigateUp()
            }

            includeCard.textViewBankDigits.text = MyCardsHomeFragment.selected.cardNumberText.formatAsHiddenCardNumber()
        }
    }

    //set up the recyclerview that contains a lsit of transactions
    private fun setUpTransactionsAdapter(statements: MutableList<Statement>) {
        val adapter = AdapterMiniStatement()
        binding.miniStatementFragmentList.adapter = adapter
        adapter.submitList(statements)
    }

}