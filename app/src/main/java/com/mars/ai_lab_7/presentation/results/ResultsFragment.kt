package com.mars.ai_lab_7.presentation.results

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mars.ai_lab_7.R
import com.mars.ai_lab_7.databinding.FragmentResultsBinding
import com.mars.ai_lab_7.presentation.results.adapter.ResultsAdapter
import com.mars.ai_lab_7.presentation.results.models.CandidateViewData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultsFragment: Fragment(R.layout.fragment_results) {
    private val viewModel: ResultsViewModel by viewModels()

    private val positionsMap: MutableMap<Int, Int> = mutableMapOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentResultsBinding.bind(view).run {
            initViews(this)
            initListeners(this)
            initObservers(this)
        }
    }

    private fun initViews(binding: FragmentResultsBinding) {
        binding.resultsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ResultsAdapter()
        }
    }

    private fun initListeners(binding: FragmentResultsBinding) {
        binding.showChartButton.setOnClickListener { viewModel.showChart() }
        binding.candidatesRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            positionsMap[checkedId]?.let { viewModel.showCandidateResults(it) }
        }
    }

    private fun initObservers(binding: FragmentResultsBinding) {
        viewModel.candidatesLiveData.observe(viewLifecycleOwner) {
            setCandidates(binding, it)
        }
        viewModel.resultsLiveData.observe(viewLifecycleOwner) {
            binding.resultsRecyclerView.adapterAction { setItems(it) }
        }
    }

    private fun RecyclerView.adapterAction(action: ResultsAdapter.() -> Unit) {
        (adapter as? ResultsAdapter)?.action()
    }

    private fun setCandidates(binding: FragmentResultsBinding, items: List<CandidateViewData>) {
        binding.candidatesRadioGroup.removeAllViews()
        positionsMap.clear()

        items.forEachIndexed { index, positionViewData ->
            val viewId = View.generateViewId()
            positionsMap[viewId] = positionViewData.id

            val view = RadioButton(context).apply {
                text = positionViewData.name
                id = viewId
            }
            binding.candidatesRadioGroup.addView(view)
        }
    }
}