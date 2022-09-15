package com.ekenya.rnd.personalfinance.ui.budgets.expenses

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.personalfinance.R
import com.ekenya.rnd.personalfinance.data.modeldto.ExpensesModel
import com.ekenya.rnd.personalfinance.databinding.FragmentExpensesPfBinding
import com.ekenya.rnd.personalfinance.ui.adapters.ExpensesAdapter
import com.ekenya.rnd.personalfinance.utils.setBackButton
import com.hadiidbouk.charts.BarData


class ExpensesFragment : Fragment() {
    private lateinit var binding: FragmentExpensesPfBinding
    private lateinit var expensesAdapter: ExpensesAdapter

    private var dataList: ArrayList<BarData> = ArrayList()

    override fun onResume() {
        super.onResume()
        val budgetMonths = resources.getStringArray(R.array.select_month)
        val arrayAdapterMonths = ArrayAdapter(
            requireContext(), R.layout.text_layout_pf, budgetMonths
        )
        binding.aCtSelectMonth.setAdapter(arrayAdapterMonths)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpensesPfBinding.inflate(
            inflater,
            container,
            false
        )
        binding.rVExpenses.isNestedScrollingEnabled = false
        setUi()

        return binding.root
    }

    private fun setUi() {
        changeDuration()

        binding.toolbar.setBackButton(R.string.expenses, requireActivity())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY <= oldScrollY) {
                    //scroll up
                    binding.fabExportReport.shrink()
                } else {
                    binding.fabExportReport.extend()
                }
            }
        }

        expensesAdapter = ExpensesAdapter(ExpensesModel.getExpenseModel())
        binding.rVExpenses.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = expensesAdapter

        }

        binding.chartProgressBar.setOnBarClickedListener {
            binding.tvDailyAverageSpendingAmount.text = "kes ${dataList[it].pinText}"
            binding.tvDailyAverageSpending.text =
                "${dataList[it].barTitle} ${getString(R.string.spending_pf)}"
        }
        dataList.clear()
        showBarCharts(daysChartProgressBar())

    }

    private fun showBarCharts(dataList: ArrayList<BarData>) {
        binding.chartProgressBar.setDataList(dataList)
        //binding.chartProgressBar.resetBarValues()
        binding.chartProgressBar.build()
    }

    private fun yearlyChartProgressBar3() = arrayListOf<BarData>(
        BarData("2020", 9.2f, "9200"),
        BarData("2021", 8.2f, "8200"),
        BarData("2022", 3.2f, "3200")
    )

    private fun monthsChartProgressBar(): ArrayList<BarData> {
        var data = BarData("Jan", 6.2f, "6200")
        dataList.add(data)

        data = BarData("Feb", 3.3f, "3300")
        dataList.add(data)

        data = BarData("Mar", 3.0f, "3000")
        dataList.add(data)

        data = BarData("Apr", 2.3f, "2300")
        dataList.add(data)

        data = BarData("May", 3.3f, "3300")
        dataList.add(data)

        data = BarData("Jun", 4.3f, "4300")
        dataList.add(data)

        data = BarData("Jul", 3.3f, "3300")
        dataList.add(data)

        data = BarData("Aug", 5.3f, "3300")
        dataList.add(data)

        data = BarData("Sep", 3.4f, "3400")
        dataList.add(data)
        data = BarData("Oct", 8f, "8000")
        dataList.add(data)

        data = BarData("Nov", 1.8f, "1800")
        dataList.add(data)

        data = BarData("Dec", 7.3f, "7300")
        dataList.add(data)
        return dataList

    }

    private fun daysChartProgressBar(): ArrayList<BarData> {

        var data = BarData("M", 6.2f, "6200")
        dataList.add(data)

        data = BarData("T", 3.3f, "3300")
        dataList.add(data)

        data = BarData("W", 3.4f, "3400")
        dataList.add(data)
        data = BarData("T", 8f, "8000")
        dataList.add(data)

        data = BarData("F", 1.8f, "1800")
        dataList.add(data)

        data = BarData("S", 7.3f, "7300")
        dataList.add(data)

        data = BarData("S", 5.5f, "5500")
        dataList.add(data)
        return dataList
    }

    private fun changeDuration() {
        binding.btnWeekly.setOnClickListener {
            binding.btnWeekly.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.btn_duration_selected_bg)
            binding.btnMonthly.setBackgroundColor(Color.TRANSPARENT)
            binding.btnQuarterly.setBackgroundColor(Color.TRANSPARENT)
            binding.btnWeekly.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.pf_white
                )
            )
            binding.btnMonthly.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.pf_dark_gray_text_color
                )
            )
            binding.btnQuarterly.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.pf_dark_gray_text_color
                )
            )
            dataList.clear()
            showBarCharts(daysChartProgressBar())
        }
        binding.btnMonthly.setOnClickListener {
            binding.btnMonthly.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.btn_duration_selected_bg)
            binding.btnWeekly.setBackgroundColor(Color.TRANSPARENT)
            binding.btnQuarterly.setBackgroundColor(Color.TRANSPARENT)

            binding.btnMonthly.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.pf_white
                )
            )
            binding.btnWeekly.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.pf_dark_gray_text_color
                )
            )
            binding.btnQuarterly.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.pf_dark_gray_text_color
                )
            )
            val layoutParams = (binding.btnMonthly.layoutParams as? ViewGroup.MarginLayoutParams)
            layoutParams?.setMargins(8, 0, 8, 0)
            binding.btnMonthly.layoutParams = layoutParams

            dataList.clear()
            showBarCharts(monthsChartProgressBar())
        }

        binding.btnQuarterly.setOnClickListener {
            binding.btnQuarterly.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.btn_duration_selected_bg)
            binding.btnWeekly.setBackgroundColor(Color.TRANSPARENT)
            binding.btnMonthly.setBackgroundColor(Color.TRANSPARENT)

            binding.btnQuarterly.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.pf_white
                )
            )
            binding.btnWeekly.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.pf_dark_gray_text_color
                )
            )
            binding.btnMonthly.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.pf_dark_gray_text_color
                )
            )

            val layoutParams = (binding.btnQuarterly.layoutParams as? ViewGroup.MarginLayoutParams)
            layoutParams?.setMargins(8, 0, 8, 0)
            binding.btnQuarterly.layoutParams = layoutParams

            dataList.clear()
            dataList.addAll(yearlyChartProgressBar3())
            showBarCharts(dataList)
        }
    }
}