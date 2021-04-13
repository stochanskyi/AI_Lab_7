package com.mars.ai_lab_7.presentation.positions

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mars.ai_lab_7.R
import com.mars.ai_lab_7.databinding.FragmentPositionsBinding
import com.mars.ai_lab_7.presentation.positions.adapter.CharacteristicsAdapter
import com.mars.ai_lab_7.presentation.positions.models.PositionViewData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PositionsFragment : Fragment(R.layout.fragment_positions) {
    private val viewModel: PositionsViewModel by viewModels()

    private val positionsMap: MutableMap<Int, Int> = mutableMapOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentPositionsBinding.bind(view).run {
            initViews(this)
            initListeners(this)
            initObservers(this)
        }
    }

    private fun initViews(binding: FragmentPositionsBinding) {
        binding.valuesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CharacteristicsAdapter(viewModel::setValue)
        }
    }

    private fun initListeners(binding: FragmentPositionsBinding) {
        binding.positionsRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            positionsMap[checkedId]?.let { viewModel.onPositionChanged(it) }
        }
        binding.resetButton.setOnClickListener { viewModel.resetValues() }
        binding.continueButton.setOnClickListener { viewModel.onContinue() }
    }

    private fun initObservers(binding: FragmentPositionsBinding) {
        viewModel.positionsLiveData.observe(viewLifecycleOwner) {
            setPositions(binding, it)
        }
        viewModel.valuesLiveData.observe(viewLifecycleOwner) {
            binding.valuesRecyclerView.adapterAction { setItems(it) }
        }
        viewModel.isValuesValid.observe(viewLifecycleOwner) {
            binding.continueButton.isEnabled = it
        }
    }

    private fun setPositions(binding: FragmentPositionsBinding, items: List<PositionViewData>) {
        binding.positionsRadioGroup.removeAllViews()
        positionsMap.clear()

        items.forEachIndexed { index, positionViewData ->
            val viewId = View.generateViewId()
            positionsMap[viewId] = positionViewData.id

            val view = RadioButton(context).apply {
                text = positionViewData.name
                id = viewId
            }
            binding.positionsRadioGroup.addView(view)
        }
    }

    private fun RecyclerView.adapterAction(action: CharacteristicsAdapter.() -> Unit) {
        (adapter as? CharacteristicsAdapter)?.action()
    }
}