package com.mars.ai_lab_7.presentation.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mars.ai_lab_7.data.analyzer.CandidatesAnalyzer
import com.mars.ai_lab_7.data.positions.CategoryInfo
import com.mars.ai_lab_7.data.positions.DefaultValuesStorage
import com.mars.ai_lab_7.presentation.results.models.CandidateViewData
import com.mars.ai_lab_7.presentation.results.models.ResultItemViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor() : ViewModel() {

    private val results = CandidatesAnalyzer.analyze(DefaultValuesStorage.positionsValues, DefaultValuesStorage.candidatesValues)

    private val _candidatesLiveData: MutableLiveData<List<CandidateViewData>> = MutableLiveData()
    val candidatesLiveData: LiveData<List<CandidateViewData>> = _candidatesLiveData

    private val _resultsLiveData: MutableLiveData<List<ResultItemViewData>> = MutableLiveData()
    val resultsLiveData: LiveData<List<ResultItemViewData>> = _resultsLiveData

    init {
        _candidatesLiveData.value = results.map { CandidateViewData(it.id, it.name) }
    }

    fun showCandidateResults(candidateId: Int) {
        val candidateResults = results.firstOrNull { it.id == candidateId } ?: return
        _resultsLiveData.value = candidateResults.createViewData()
    }

    private fun CategoryInfo.createViewData(): List<ResultItemViewData> {
        return characteristicsValues.map { ResultItemViewData(it.name, it.value?.toString() ?: "") }
    }

    fun showChart() {
        //TODO
    }
}