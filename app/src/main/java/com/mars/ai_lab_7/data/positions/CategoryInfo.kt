package com.mars.ai_lab_7.data.positions

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
    data class CategoryInfo(
    val id: Int,
    val name: String,
    val characteristicsValues: List<CharacteristicInfo>
) : Parcelable {
    fun reset() = characteristicsValues.forEach { it.reset() }

    val areValuesValid: Boolean get() = characteristicsValues.all { it.value != null }
}