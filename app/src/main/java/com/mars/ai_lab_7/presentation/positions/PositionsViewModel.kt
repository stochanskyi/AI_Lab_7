package com.mars.ai_lab_7.presentation.positions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mars.ai_lab_7.data.positions.DefaultValuesStorage
import com.mars.ai_lab_7.presentation.positions.models.CharacteristicViewData
import com.mars.ai_lab_7.presentation.positions.models.PositionViewData
import com.mars.ai_lab_7.utils.livedata.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PositionsViewModel @Inject constructor() : ViewModel() {

    private val positions = DefaultValuesStorage.positionsValues

    private var selectedPosition: Int = 0

    private val _positionsLiveData: MutableLiveData<List<PositionViewData>> =
        SingleLiveData(positions.map { PositionViewData(it.id, it.name) })
    val positionsLiveData: LiveData<List<PositionViewData>> = _positionsLiveData

    private val _valuesLiveData: MutableLiveData<List<CharacteristicViewData>> = SingleLiveData()
    val valuesLiveData: LiveData<List<CharacteristicViewData>> = _valuesLiveData

    private val _isValuesValid: MutableLiveData<Boolean> = MutableLiveData()
    val isValuesValid: LiveData<Boolean> = _isValuesValid

    init {
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

    fun onContinue() {
        //TODO
    }

    private fun validateValues() {
        _isValuesValid.value = positions.all { it.areValuesValid }
    }

    private fun createCharacteristicsViewData(positionIndex: Int): List<CharacteristicViewData> {
        return positions.getOrNull(positionIndex)?.characteristicsValues?.map {
            CharacteristicViewData(it.id, it.name, it.value?.toString() ?: "")
        } ?: emptyList()
    }
}