package com.ekenya.rnd.personalfinance.ui.budgets.managebudget


import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ekenya.rnd.personalfinance.R
import com.ekenya.rnd.personalfinance.databinding.FragmentManageBudgetPfBinding
import com.ekenya.rnd.personalfinance.utils.BUDGET_AMOUNT_EXISTS
import com.ekenya.rnd.personalfinance.utils.setBackButton
import com.github.eclectics.charting.components.*
import com.github.eclectics.charting.data.BarEntry
import com.github.eclectics.charting.data.Entry
import com.github.eclectics.charting.data.LineData
import com.github.eclectics.charting.data.LineDataSet
import com.github.eclectics.charting.formatter.IAxisValueFormatter
import com.github.eclectics.charting.formatter.IFillFormatter
import com.github.eclectics.charting.interfaces.datasets.ILineDataSet
import com.github.eclectics.charting.utils.ColorTemplate
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ManageBudgetFragment : Fragment() {
    private lateinit var binding: FragmentManageBudgetPfBinding


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
    ): View? {
        binding = FragmentManageBudgetPfBinding.inflate(inflater, container, false)
        setUi()
        binding.toolbar.setBackButton(R.string.manage_budget, requireActivity())

        return binding.root
    }

    private fun setUi() {

        //setDayBarchart()
        renderData()
        changeDuration()

        binding.tvBudgetAmountExists.text = "Kes ${BUDGET_AMOUNT_EXISTS}"
        binding.tvBudgetAmountExists.setOnClickListener {
            BUDGET_AMOUNT_EXISTS = ""
            val bottomSheetDialogFragment: BottomSheetDialogFragment =
                EditBudgetFragment()
            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager,
                bottomSheetDialogFragment.tag
            )
        }

    }


    private fun renderData() {
        binding.apply {
            lineChart.setTouchEnabled(false)
            lineChart.setPinchZoom(false)
            lineChart.description.text = ""
            lineChart.axisRight.isEnabled = false
            lineChart.xAxis.isDrawGridLinesBehindDataEnabled
            lineChart.axisLeft.isDrawGridLinesBehindDataEnabled
            lineChart.xAxis.gridColor = resources.getColor(R.color.pf_light_gray)
            lineChart.axisLeft.gridColor = resources.getColor(R.color.pf_light_gray)
            lineChart.axisLeft.axisLineColor = resources.getColor(R.color.empty)
            lineChart.data = setData()
        }


        val llXAxis = LimitLine(13f, "Index 10")
        llXAxis.lineWidth = 4f
        llXAxis.enableDashedLine(10f, 10f, 0f)
        llXAxis.labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
        llXAxis.textSize = 10f

        val xAxis: XAxis = binding.lineChart.xAxis
        //xAxis.enableGridDashedLine(10f, 10f, 0f)
        xAxis.axisMaximum = 13f
        xAxis.axisMinimum = 0f
        //xAxis.valueFormatter = MonthValueFormatter()
        val xAxisLabel: ArrayList<String> = ArrayList()
        xAxisLabel.add("")
        xAxisLabel.add("Jan")
        xAxisLabel.add("Feb")
        xAxisLabel.add("Mar")
        xAxisLabel.add("Apr")
        xAxisLabel.add("May")
        xAxisLabel.add("Jun")
        xAxisLabel.add("Jul")
        xAxisLabel.add("Aug")
        xAxisLabel.add("Sep")
        xAxisLabel.add("Oct")
        xAxisLabel.add("Nov")
        xAxisLabel.add("Dec")
        xAxis.valueFormatter =
            IAxisValueFormatter { value,
                                  axis ->
                if (value == -1f || value >= xAxisLabel.size)
                    "" else
                    xAxisLabel[value.toInt()]
            }
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.setDrawLimitLinesBehindData(true)

        val ll1 = LimitLine(23f, "Budget: KES 40,000")
        ll1.lineWidth = 2f
        ll1.textColor = Color.RED
        ll1.enableDashedLine(5f, 5f, 0f)
        ll1.labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
        ll1.textSize = 12f

        val leftAxis: YAxis = binding.lineChart.axisLeft
        leftAxis.removeAllLimitLines()
        leftAxis.addLimitLine(ll1)
        leftAxis.axisMaximum = 25f
        leftAxis.axisMinimum = 1f

        val yAxisLabel: ArrayList<String> = ArrayList()
        yAxisLabel.add("10k")
        yAxisLabel.add("20k")
        yAxisLabel.add("30k")
        yAxisLabel.add("40k")
        yAxisLabel.add("50k")
        yAxisLabel.add("60k")

        leftAxis.setCenterAxisLabels(true)

//        leftAxis.valueFormatter =
//            IAxisValueFormatter { value,
//                                  axis ->
//                if (value == 0f || value >= yAxisLabel.size) {
//                    ""
//                } else {
//                    yAxisLabel[value.toInt()]
//                }
//            }

        leftAxis.valueFormatter = DayValueFormatter()
        leftAxis.setDrawLimitLinesBehindData(false)
        legend()
    }

    private fun setData(): LineData {
        val set1: LineDataSet = LineDataSet(firstLineValues(), "Spent")
        set1.setDrawIcons(false)
        set1.setDrawCircles(false)
        set1.setDrawValues(false)
        set1.setDrawCircleHole(false)
        set1.setDrawFilled(true)

        //set1.lineWidth = 1f
        //set1.formLineWidth = 1f
        set1.fillFormatter =
            IFillFormatter { dataSet, dataProvider -> binding.lineChart.axisLeft.axisMinimum }
        set1.formSize = 15f
        val drawable: Drawable? =
            ContextCompat.getDrawable(requireActivity().application, R.drawable.fade_line_blue_pf)
        drawable!!.alpha = 200
        set1.fillDrawable = drawable
        set1.mode = LineDataSet.Mode.CUBIC_BEZIER
        set1.entries = firstLineValues()

        //Dataset 2
        val set2: LineDataSet = LineDataSet(secondLineValues(), "Forecast")
        set2.axisDependency = YAxis.AxisDependency.LEFT
        set2.color = resources.getColor(R.color.pf_blue_color)
        //set2.lineWidth = 2f
        //set2.fillAlpha = 65
        set2.entries = secondLineValues()
        set2.highLightColor = resources.getColor(R.color.pf_blue_color)
        set2.fillFormatter =
            IFillFormatter { dataSet, dataProvider -> binding.lineChart.axisLeft.axisMinimum }
        set2.mode = LineDataSet.Mode.LINEAR

        set2.setDrawCircleHole(false)
        set2.setDrawIcons(false)
        set2.setDrawCircles(false)
        set2.setDrawValues(false)
        set2.setDrawFilled(false)

        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(set1)
        dataSets.add(set2)
        val data = LineData(dataSets)
        return data
        // }
    }

    private fun legend() {
        val legend = binding.lineChart.legend
        legend.isEnabled = true
        legend.textColor = Color.BLACK
        legend.textSize = 15F
        legend.form = Legend.LegendForm.LINE
        legend.formSize = 20f
        legend.xEntrySpace = 15f
        legend.formToTextSpace = 10f
        legend.yEntrySpace = 10f
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER

        // val legendEntry=LegendEntry[2]
    }

    private fun firstLineValues(): ArrayList<Entry> {
        val values: ArrayList<Entry> = ArrayList()
        values.add(Entry(0F, 20F))
        values.add(Entry(1F, 19F))
        values.add(Entry(2F, 16F))
        values.add(Entry(3F, 15F))
        values.add(Entry(4F, 16F))
        values.add(Entry(5F, 19F))
        values.add(Entry(6F, 20F))
        return values
    }

    private fun secondLineValues(): ArrayList<Entry> {
        val values: ArrayList<Entry> = ArrayList()
        values.add(Entry(6F, 20F))
        values.add(Entry(10F, 23F))
        return values
    }

    private fun changeDuration() {
        binding.btnWeekly.setOnClickListener {
            binding.btnWeekly.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.btn_duration_selected_bg)
            binding.btnMonthly.setBackgroundColor(Color.TRANSPARENT)
            binding.btnMonthly.setBackgroundColor(Color.TRANSPARENT)
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
            binding.btnMonthly.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.pf_dark_gray_text_color
                )
            )
        }

        binding.btnMonthly.setOnClickListener {
            binding.btnMonthly.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.btn_duration_selected_bg)
            binding.btnWeekly.setBackgroundColor(Color.TRANSPARENT)

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

            val layoutParams = (binding.btnMonthly.layoutParams as? ViewGroup.MarginLayoutParams)
            layoutParams?.setMargins(0, 0, 0, 0)
            binding.btnMonthly.layoutParams = layoutParams
        }
    }

    private fun setDayBarchart() {
        //val yVals1 = ArrayList<BarEntry>()
        val yVals1 = ArrayList<Entry>()
        yVals1.add(BarEntry(0f, 6f))
        yVals1.add(BarEntry(1f, 1f))
        yVals1.add(BarEntry(2f, 2f))
        yVals1.add(BarEntry(3f, 3f))
        yVals1.add(BarEntry(4f, 5f))
        yVals1.add(BarEntry(5f, 4f))
        yVals1.add(BarEntry(6f, 7f))

        val set1 = LineDataSet(yVals1, "")
        set1.setColors(ColorTemplate.rgb("439CFF"))
        val dataSets = ArrayList<ILineDataSet>()

        val drawable: Drawable? =
            ContextCompat.getDrawable(requireActivity().application, R.drawable.fade_line_blue_pf)
        drawable!!.alpha = 200
        set1.fillDrawable = drawable
        set1.setDrawFilled(true)

        set1.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataSets.add(set1)

        val data = LineData(dataSets)
        data.setValueTextSize(7f)
        //data.barWidth = 0.5f

        val formatter = object : IAxisValueFormatter {
            // the labels that should be drawn on the XAxis
            val quarter = arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
            override fun getFormattedValue(value: Float, axis: AxisBase?): String {
                return quarter[value.toInt()]
            }
        }

        val xAxis = binding.lineChart.xAxis
        xAxis.granularity = 1f // minimum axis-step (interval) is 1
        xAxis.valueFormatter = formatter
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textSize = 10f
        xAxis.textColor = Color.BLACK
        xAxis.setDrawAxisLine(false)

        binding.lineChart.setTouchEnabled(false)
        binding.lineChart.data = data
        binding.lineChart.animateXY(2000, 2000)
        //binding.lineChart.setFitBars(true)
        binding.lineChart.description.text = ""

    }


}