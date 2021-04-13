package com.mars.ai_lab_7.data.positions

import com.mars.ai_lab_7.data.texts.TextsStorage

object DefaultValuesStorage {

    private val positionsCharacteristicValues: List<List<Float>> = listOf(
        listOf(0.9F, 0.9F, 0.8F, 0.4F, 0.5F, 0.3F, 0.6F, 0.2F, 0.9F, 0.8F),
        listOf(0.8F, 0.5F, 0.9F, 0.3F, 0.1F, 0.2F, 0.2F, 0.2F, 0.5F, 0.5F),
        listOf(0.3F, 0.9F, 0.6F, 0.5F, 0.9F, 0.8F, 0.9F, 0.8F, 0.6F, 0.3F),
        listOf(0.5F, 0.4F, 0.5F, 0.5F, 0.2F, 0.2F, 0.3F, 0.3F, 0.9F, 0.8F),
        listOf(0.7F, 0.8F, 0.8F, 0.2F, 0.6F, 0.2F, 0.2F, 0.3F, 0.3F, 0.2F),
    )

    private val candidatesCharacteristicValues: List<List<Float>> = listOf(
        listOf(0.9F, 0.6F, 0.5F, 0.5F, 1F, 0.4F, 0.5F, 0.5F, 0.8F, 0.3F),
        listOf(0.8F, 0.4F, 0.2F, 0.9F, 0.6F, 0.5F, 0.8F, 0.6F, 1F, 0.5F),
        listOf(0.7F, 0.8F, 0.3F, 0.5F, 0.5F, 1F, 0.9F, 0.7F, 0.2F, 0.9F),
        listOf(0.9F, 0.5F, 0.5F, 0.5F, 0.7F, 0.7F, 0.5F, 0.6F, 0.5F, 0.6F),
        listOf(1F, 0.6F, 0.9F, 0.2F, 0.4F, 0.8F, 0.4F, 0.5F, 0.6F, 0.8F),
    )

    val positionsValues: List<PositionInfo> = TextsStorage.positions.mapIndexed { index, name ->
        PositionInfo(
            index,
            name,
            TextsStorage.characteristics.mapIndexed
            { i, v ->
                CharacteristicInfo(i, v, positionsCharacteristicValues[index][i])
            }
        )
    }

    val candidatesValues: List<CandidateInfo> = TextsStorage.candidates.mapIndexed { index, name ->
        CandidateInfo(
            index,
            name,
            TextsStorage.characteristics.zip(candidatesCharacteristicValues[index]).toMap()
        )
    }
}