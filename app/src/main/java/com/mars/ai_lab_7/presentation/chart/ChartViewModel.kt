package com.mars.ai_lab_7.presentation.chart

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.mars.ai_lab_7.data.positions.CategoryInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor() : ViewModel() {

    private val categoryColors: List<Int> = listOf(
        Color.rgb(255, 0, 0),
        Color.rgb(255, 255, 0),
        Color.rgb(255, 0, 255),
        Color.rgb(0, 0, 255),
        Color.rgb(0, 255, 0),
    )
    private lateinit var values: List<CategoryInfo>

    private val _barDataLiveData: MutableLiveData<BarData> = MutableLiveData()
    val barDataLiveData: LiveData<BarData> = _barDataLiveData

    fun setup(values: List<CategoryInfo>) {
        this.values = values
        _barDataLiveData.value = createViewData()
    }

    private fun createViewData(): BarData {
        val dataSets: MutableList<BarDataSet> = mutableListOf()
        for (i in values.first().characteristicsValues.indices) {
            val items = values.mapIndexed { idx, data ->
                BarEntry(
                    idx.toFloat(),
                    data.characteristicsValues[i].valueOrDefault()
                )
            }
            dataSets += BarDataSet(items, values.first().characteristicsValues[i].name).apply {
                color = categoryColors[i]
            }
        }
        return BarData(dataSets.toList())
    }
}