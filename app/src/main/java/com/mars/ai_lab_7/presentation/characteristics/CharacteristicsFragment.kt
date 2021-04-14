package com.mars.ai_lab_7.presentation.characteristics

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mars.ai_lab_7.R
import com.mars.ai_lab_7.databinding.FragmentPositionsBinding
import com.mars.ai_lab_7.presentation.characteristics.adapter.CharacteristicsAdapter
import com.mars.ai_lab_7.presentation.characteristics.behaviour.CandidatesBehaviour
import com.mars.ai_lab_7.presentation.characteristics.behaviour.PositionsBehaviour
import com.mars.ai_lab_7.presentation.characteristics.models.CategoryViewData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacteristicsFragment : Fragment(R.layout.fragment_positions) {
    private val viewModel: CharacteristicsViewModel by viewModels()

    private val positionsMap: MutableMap<Int, Int> = mutableMapOf()

    private val args: CharacteristicsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setup(args.behaviour ?: PositionsBehaviour())
    }

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
        viewModel.isValuesValidLiveData.observe(viewLifecycleOwner) {
            binding.continueButton.isEnabled = it
        }
        viewModel.screenDescriptionResLiveData.observe(viewLifecycleOwner) {
            binding.titleTextView.text = getString(it)
        }
        viewModel.openCandidatesLiveData.observe(viewLifecycleOwner) {
            val action = CharacteristicsFragmentDirections.navigateToCandidates(CandidatesBehaviour())
            Navigation.findNavController(binding.root).navigate(action)
        }
        viewModel.openResultsLiveData.observe(viewLifecycleOwner) {
            Navigation.findNavController(binding.root).navigate(R.id.navigate_to_results)
        }
    }

    private fun setPositions(binding: FragmentPositionsBinding, items: List<CategoryViewData>) {
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