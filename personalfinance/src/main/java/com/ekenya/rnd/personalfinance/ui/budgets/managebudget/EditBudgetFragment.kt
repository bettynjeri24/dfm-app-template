package com.ekenya.rnd.personalfinance.ui.budgets.managebudget

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekenya.rnd.personalfinance.R
import com.ekenya.rnd.personalfinance.databinding.FragmentEditBudgetPfBinding
import com.ekenya.rnd.personalfinance.utils.BUDGET_AMOUNT_EXISTS
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditBudgetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentEditBudgetPfBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBudgetPfBinding.inflate(inflater, container, false)
        setUi()
        return binding.root
    }

    private fun setUi() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireView().parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))

        binding.btnEditBudget.setOnClickListener {
            if (binding.etEditBudget.text.toString().isNotEmpty()) {
                BUDGET_AMOUNT_EXISTS = binding.etEditBudget.text.toString()
                dismiss()
            } else {
                binding.etEditBudget.error = "Please enter amount"
            }


        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetTheme);

    }

    override fun onStart() {
        super.onStart()
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels

        dialog?.window?.setLayout(width - 4, ViewGroup.LayoutParams.MATCH_PARENT)
        //dialog?.window?.setLayout(width , height)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        //
        return dialog
    }
}