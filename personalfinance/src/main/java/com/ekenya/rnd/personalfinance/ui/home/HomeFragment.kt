package com.ekenya.rnd.personalfinance.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.personalfinance.R
import com.ekenya.rnd.personalfinance.data.modeldto.ExpensesModel
import com.ekenya.rnd.personalfinance.databinding.FragmentHomePersonalFinanceBinding
import com.ekenya.rnd.personalfinance.utils.setBackButton
import com.github.eclectics.charting.animation.Easing.EaseInOutQuad
import com.github.eclectics.charting.components.Legend
import com.github.eclectics.charting.data.Entry
import com.github.eclectics.charting.data.PieData
import com.github.eclectics.charting.data.PieDataSet
import com.github.eclectics.charting.data.PieEntry
import com.github.eclectics.charting.highlight.Highlight
import com.github.eclectics.charting.listener.OnChartValueSelectedListener
import com.github.eclectics.charting.utils.ColorTemplate
import timber.log.Timber
import javax.inject.Inject


class HomeFragment : BaseDaggerFragment() {
    @JvmField
    @Inject
    var viewModelFactory: ViewModelProvider.Factory? = null
    private var homeViewModel: HomeViewModel? = null
    private lateinit var binding: FragmentHomePersonalFinanceBinding
    val yValues = ArrayList<PieEntry>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(requireActivity(), viewModelFactory!!).get<HomeViewModel>(
            HomeViewModel::class.java
        )
        binding = FragmentHomePersonalFinanceBinding.inflate(inflater, container, false)
        setUi()

        return binding.root
    }

    private fun setUi() {
        yValues.clear()
        binding.toolbar.setBackButton(R.string.title_personal_finance, requireActivity())
        initPieChart(ExpensesModel.getExpenseModel())


        binding.btnCreateNewBudget.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_createBudgetFragment)
        }
        binding.tvManageBudget.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_manageBudgetFragment)
        }
        binding.tvSeeMore.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_expensesFragment)
        }

        binding.tvBudgetSetExists.setOnClickListener {
            binding.cLBudgetExists.visibility = View.GONE
            binding.cLNoBudgetFound.visibility = View.VISIBLE
        }

        binding.tvNoBudgetSet.setOnClickListener {
            binding.cLBudgetExists.visibility = View.VISIBLE
            binding.cLNoBudgetFound.visibility = View.GONE
        }


    }


    private fun initPieChart(list: ArrayList<ExpensesModel>) {
        binding.pieChart.setUsePercentValues(true)
        binding.pieChart.description.text = ""
        //hollow pie chart
        binding.pieChart.setTouchEnabled(true)
        binding.pieChart.onTouchListener.touchMode
        //adding padding
        binding.pieChart.setExtraOffsets(25f, 0f, 25f, 0f)
        binding.pieChart.isRotationEnabled = false
        binding.pieChart.dragDecelerationFrictionCoef = 1f
        binding.pieChart.spin(500, 0F, -360f, EaseInOutQuad)

        //create hole in center
        binding.pieChart.holeRadius = 65F
        binding.pieChart.isDrawHoleEnabled = true
        binding.pieChart.setHoleColor(Color.WHITE)

        //add text in center
        binding.pieChart.setDrawCenterText(true)
        binding.pieChart.setDrawEntryLabels(false)


        var total = 0.0
        for (item in list) {
            total += item.amountExpense.toFloat()
        }
        for (item in list) {
            yValues.add(
                PieEntry(
                    item.amountExpense.toFloat(), item.titleExpense
                )
            )
        }
        val dataSet = PieDataSet(yValues, "")
        val data = PieData(dataSet)
        // In Percentage
        //data.setValueFormatter(PercentFormatter())
        //dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        //dataSet.colors = colors
        dataSet.setColors(*ColorTemplate.JOYFUL_COLORS)
        binding.pieChart.data = data
        data.setValueTextSize(15f)
        dataSet.setDrawValues(false)


        if (total < 1) {
            binding.pieChart.centerText = "You do not have any expenses"
        } else {
            binding.pieChart.centerText = "All Kes $total"

        }

        binding.pieChart.invalidate()
        binding.pieChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                // display msg when value selected
                if (e == null) return
                //dataSet.setValueTextColor(colors.get(e.getXIndex()))
                //binding.pieChart.centerText = yValues.get(e.getXIndex()).toString() + " \n " + e.getVal()
                //binding.pieChart.centerText = yValues.get(e.x.toInt()).toString()

                binding.pieChart.centerText =
                    "${list[h!!.x.toInt()].titleExpense.toString()}\nkes ${e.y}"
                Timber.e(
                    "VAL SELECTED %s",
                    "Value: " + e.y + ", index: " + h!!.x
                            + ", DataSet index: " + h.dataSetIndex + ", Label: " + e.describeContents()
                )
            }

            override fun onNothingSelected() {}
        })


        setLegend()

    }

    private fun setLegend() {
        //legend attributes
        val legend: Legend = binding.pieChart.legend
        legend.form = Legend.LegendForm.CIRCLE
        legend.textSize = 12f
        legend.formSize = 20f
        legend.formToTextSpace = 10f
        //to wrap legend text
        legend.isWordWrapEnabled = true
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(false)
        legend.yEntrySpace = 6f
        legend.xEntrySpace = 6f
        legend.xOffset = 10f

    }


}