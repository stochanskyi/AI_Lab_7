package com.mars.ai_lab_7.presentation.characteristics.behaviour

import com.mars.ai_lab_7.R
import com.mars.ai_lab_7.data.positions.CategoryInfo
import com.mars.ai_lab_7.data.positions.DefaultValuesStorage
import kotlinx.parcelize.Parcelize

@Parcelize
class CandidatesBehaviour : BaseCharacteristicsBehaviour() {


    override fun provideScreenTitleRes() = R.string.candidates_title

    override fun provideScreenDescriptionRes()  = R.string.candidates_description

    override fun onContinue() {
        behaviourTarget?.navigateToResults()
    }

    override fun provideItems(): List<CategoryInfo> =DefaultValuesStorage.candidatesValues

}