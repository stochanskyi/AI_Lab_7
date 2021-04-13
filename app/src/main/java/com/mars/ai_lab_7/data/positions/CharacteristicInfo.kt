package com.mars.ai_lab_7.data.positions

data class CharacteristicInfo(
    val id: Int,
    val name: String,
    private val defaultValue: Float
) {
    var value: Float? = defaultValue

    fun reset() {
        value = defaultValue
    }
}