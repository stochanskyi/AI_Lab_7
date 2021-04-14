package com.mars.ai_lab_7.data.positions

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacteristicInfo(
    val id: Int,
    val name: String,
    private val defaultValue: Float
) : Parcelable {
    var value: Float? = defaultValue

    fun valueOrDefault(): Float = value ?: defaultValue

    fun reset() {
        value = defaultValue
    }
}