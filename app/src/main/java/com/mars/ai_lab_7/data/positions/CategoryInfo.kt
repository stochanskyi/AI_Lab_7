package com.mars.ai_lab_7.data.positions

data class CategoryInfo(
    val id: Int,
    val name: String,
    val characteristicsValues: List<CharacteristicInfo>
) {
    fun reset() = characteristicsValues.forEach { it.reset() }

    val areValuesValid: Boolean get() = characteristicsValues.all { it.value != null }
}