package com.mars.ai_lab_7.presentation.characteristics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mars.ai_lab_7.data.positions.CategoryInfo
import com.mars.ai_lab_7.presentation.characteristics.behaviour.CharacteristicsBehaviour
import com.mars.ai_lab_7.presentation.characteristics.behaviour.CharacteristicsBehaviourTarget
import com.mars.ai_lab_7.presentation.characteristics.models.CategoryViewData
import com.mars.ai_lab_7.presentation.characteristics.models.CharacteristicViewData
import com.mars.ai_lab_7.utils.livedata.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacteristicsViewModel @Inject constructor() : ViewModel(), CharacteristicsBehaviourTarget {

    private lateinit var positions: List<CategoryInfo>

    private lateinit var behaviour: CharacteristicsBehaviour

    private var selectedPosition: Int = 0

    private val _positionsLiveData: MutableLiveData<List<CategoryViewData>> = MutableLiveData()
    val positionsLiveData: LiveData<List<CategoryViewData>> = _positionsLiveData

    private val _valuesLiveData: MutableLiveData<List<CharacteristicViewData>> = SingleLiveData()
    val valuesLiveData: LiveData<List<CharacteristicViewData>> = _valuesLiveData

    private val _isValuesValidLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isValuesValidLiveData: LiveData<Boolean> = _isValuesValidLiveData

    private val _screenDescriptionResLiveData: MutableLiveData<Int> = MutableLiveData()
    val screenDescriptionResLiveData: LiveData<Int> = _screenDescriptionResLiveData

    private val _openCandidatesLiveData: SingleLiveData<Unit> = SingleLiveData()
    val openCandidatesLiveData: LiveData<Unit> = _openCandidatesLiveData

    private val _openResultsLiveData: SingleLiveData<Unit> = SingleLiveData()
    val openResultsLiveData: LiveData<Unit> = _openResultsLiveData

    fun setup(behaviour: CharacteristicsBehaviour) {
        this.behaviour = behaviour

        behaviour.setTarget(this)
        behaviour.provideItems().let {
            positions = it
            _positionsLiveData.value = it.createPositionsViewData()
        }

        _screenDescriptionResLiveData.value = behaviour.provideScreenDescriptionRes()
        validateValues()
    }

    fun setValue(id: Int, value: String) {
        positions[selectedPosition].characteristicsValues.firstOrNull { it.id == id }?.value =
            value.toFloatOrNull()

        validateValues()
    }

    fun onPositionChanged(id: Int) {
        _valuesLiveData.value = positions.indexOfFirst { it.id == id }
            .takeIf { it >= 0 }
            ?.also { selectedPosition = it }
            ?.let { createCharacteristicsViewData(it) } ?: return
    }

    fun resetValues() {
        positions.forEach { it.reset() }
        _valuesLiveData.value = createCharacteristicsViewData(selectedPosition)
        validateValues()
    }

    fun setToZero() {
        positions.forEach { it.characteristicsValues.forEach { it.value = 0f } }
        _valuesLiveData.value = createCharacteristicsViewData(selectedPosition)
        validateValues()
    }

    fun onContinue() {
        behaviour.onContinue()
    }

    private fun validateValues() {
        _isValuesValidLiveData.value = positions.all { it.areValuesValid }
    }

    private fun createCharacteristicsViewData(positionIndex: Int): List<CharacteristicViewData> {
        return positions.getOrNull(positionIndex)?.characteristicsValues?.map {
            CharacteristicViewData(it.id, it.name, it.value?.toString() ?: "")
        } ?: emptyList()
    }

    private fun List<CategoryInfo>.createPositionsViewData(): List<CategoryViewData> {
        return map { CategoryViewData(it.id, it.name) }
    }

    override fun navigateToCandidates() {
        _openCandidatesLiveData.call()
    }

    override fun navigateToResults() {
        _openResultsLiveData.call()
    }
}