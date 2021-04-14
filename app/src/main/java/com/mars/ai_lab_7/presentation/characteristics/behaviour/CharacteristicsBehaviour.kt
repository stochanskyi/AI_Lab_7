package com.mars.ai_lab_7.presentation.characteristics.behaviour

import android.os.Parcelable
import com.mars.ai_lab_7.data.positions.CategoryInfo

interface CharacteristicsBehaviour : Parcelable {
    fun setTarget(target: CharacteristicsBehaviourTarget)

    fun provideScreenTitleRes(): Int

    fun provideScreenDescriptionRes(): Int

    fun onContinue()

    fun provideItems(): List<CategoryInfo>
}