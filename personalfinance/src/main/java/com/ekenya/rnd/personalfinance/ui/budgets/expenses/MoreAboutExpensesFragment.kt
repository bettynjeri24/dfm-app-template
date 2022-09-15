package com.ekenya.rnd.personalfinance.ui.budgets.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.personalfinance.R
import com.ekenya.rnd.personalfinance.databinding.FragmentMoreAboutExpensesPfBinding
import com.ekenya.rnd.personalfinance.ui.SharedViewModel
import com.ekenya.rnd.personalfinance.ui.adapters.multipleview.MoreExpenseRecyclerViewAdapter
import com.ekenya.rnd.personalfinance.ui.adapters.multipleview.MoreExpenseRecyclerViewItem
import com.ekenya.rnd.personalfinance.utils.TOTAL_EXPENSES
import com.ekenya.rnd.personalfinance.utils.setBackButton
import timber.log.Timber
import java.util.*

class MoreAboutExpensesFragment : Fragment() {
    private lateinit var binding: FragmentMoreAboutExpensesPfBinding
    private val listItem: ArrayList<MoreExpenseRecyclerViewItem> = ArrayList()
    private lateinit var moreExpenseRecyclerViewAdapter: MoreExpenseRecyclerViewAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoreAboutExpensesPfBinding.inflate(
            inflater,
            container,
            false
        )
        setUi()

        return binding.root
    }

    private fun setUi() {
        binding.toolbar.setBackButton(R.string.funds_transfer_expense, requireActivity())
        sharedViewModel.assetData.observe(viewLifecycleOwner) {

            it.map {
                TOTAL_EXPENSES += it.amountMoreExpense
                //Timber.e("************************ ${TOTAL_EXPENSES}")
            }
            binding.tvTotalSpentAmount.text = "kes ${TOTAL_EXPENSES.toString()}"
            Timber.e("************************ ${TOTAL_EXPENSES}")

            val assetMap: Map<String, List<MoreExpenseRecyclerViewItem.BodyMoreExpense>?> =
                listToMap(it)

            for (date in assetMap.keys) {
                val header = MoreExpenseRecyclerViewItem.HeaderMoreExpense(date)
                listItem.add(header)
                for (event in assetMap[date]!!) {
                    val item = MoreExpenseRecyclerViewItem.BodyMoreExpense(
                        titleMoreExpense = event.titleMoreExpense,
                        durationMoreExpense = event.durationMoreExpense,
                        amountMoreExpense = event.amountMoreExpense,
                        timeMoreExpense = event.timeMoreExpense
                    )
                    listItem.add(item)
                }
            }
            moreExpenseRecyclerViewAdapter =
                MoreExpenseRecyclerViewAdapter(listItem)
            inflateRecyclerView()
        }

    }

    private fun inflateRecyclerView() {
        if (listItem.isEmpty()) {
            Timber.e("ASSET DATA 1 $listItem")
            /*binding.iVEmptyBox.visibilityView(true)
            binding.tvEmptyBox.visibilityView(true)*/
        } else if (listItem.isNotEmpty()) {
            Timber.e("ASSET DATA 2 $listItem")
//            binding.iVEmptyBox.visibilityView(false)
//            binding.tvEmptyBox.visibilityView(false)
            binding.rvListOfAssets.apply {
                setHasFixedSize(true)
                adapter = moreExpenseRecyclerViewAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        } else {
            Timber.e("ASSET DATA 3 $listItem")
            /*binding.iVEmptyBox.visibilityView(true)
            binding.tvEmptyBox.visibilityView(true)*/
        }
    }

    private fun listToMap(events: List<MoreExpenseRecyclerViewItem.BodyMoreExpense>): Map<String, MutableList<MoreExpenseRecyclerViewItem.BodyMoreExpense>?> {
        val map: MutableMap<String, MutableList<MoreExpenseRecyclerViewItem.BodyMoreExpense>?> =
            TreeMap()
        for (event in events) {
            var value: MutableList<MoreExpenseRecyclerViewItem.BodyMoreExpense>? =
                map[event.durationMoreExpense]
            if (value == null) {
                value = ArrayList()
                map[event.durationMoreExpense] = value
            }
            value.add(event)
        }
        return map
    }
}