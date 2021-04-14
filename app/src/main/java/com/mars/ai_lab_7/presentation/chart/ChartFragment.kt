package com.mars.ai_lab_7.presentation.chart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.mars.ai_lab_7.R
import com.mars.ai_lab_7.databinding.FragmentResultsChartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChartFragment : Fragment(R.layout.fragment_results_chart) {
    private val viewModel: ChartViewModel by viewModels()
    private val args: ChartFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentResultsChartBinding.bind(view).run {
            viewModel.setup(args.values.toList())
            initViews(this)
            initObservers(this)
        }
    }

    private fun initViews(binding: FragmentResultsChartBinding) {
        binding.chart1.apply {
            description.setEnabled(false)

            setPinchZoom(false)

            setDrawBarShadow(false)

            setDrawGridBackground(false)

            setAutoScaleMinMaxEnabled(true)
        }
    }

    private fun initObservers(binding: FragmentResultsChartBinding) {
        viewModel.barDataLiveData.observe(viewLifecycleOwner) {
            it.setValueFormatter(LargeValueFormatter())
            binding.chart1.data = it


            binding.chart1.data.notifyDataChanged()
            binding.chart1.notifyDataSetChanged()
            binding.chart1.groupBars(0F, 1f, 0.5f)
            binding.chart1.invalidate()

            binding.chart1.xAxis.axisMinimum = 0f

            binding.chart1.xAxis.axisMaximum = 40f
        }
    }
}