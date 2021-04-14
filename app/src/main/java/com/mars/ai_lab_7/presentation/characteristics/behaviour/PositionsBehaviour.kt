package com.mars.ai_lab_7.presentation.characteristics.behaviour

import com.mars.ai_lab_7.R
import com.mars.ai_lab_7.data.positions.CategoryInfo
import com.mars.ai_lab_7.data.positions.DefaultValuesStorage
import kotlinx.parcelize.Parcelize

@Parcelize
class PositionsBehaviour : BaseCharacteristicsBehaviour() {

    override fun provideScreenTitleRes() = R.string.positions_title

    override fun provideScreenDescriptionRes() = R.string.positions_description

    override fun onContinue() {
        behaviourTarget?.navigateToCandidates()
    }

    override fun provideItems(): List<CategoryInfo> = DefaultValuesStorage.positionsValues
}