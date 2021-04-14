package com.mars.ai_lab_7.data.analyzer

import com.mars.ai_lab_7.data.positions.CategoryInfo
import com.mars.ai_lab_7.data.positions.CharacteristicInfo
import kotlin.math.min

object CandidatesAnalyzer {

    fun analyze(positions: List<CategoryInfo>, candidates: List<CategoryInfo>): List<CategoryInfo> {
        return candidates.mapIndexed { index, categoryInfo ->
            CategoryInfo(
                categoryInfo.id,
                categoryInfo.name,
                positions.map {
                    CharacteristicInfo(
                        it.id,
                        it.name,
                        calculatePositionsMatch(
                            categoryInfo.characteristicsValues,
                            it.characteristicsValues
                        )
                    )
                }
            )
        }
    }

    private fun calculatePositionsMatch(
        candidateCharacteristics: List<CharacteristicInfo>,
        positionCharacteristics: List<CharacteristicInfo>
    ): Float {
        val l = mutableListOf<Float>()
        for (i in candidateCharacteristics.indices) {
            l += min(
                candidateCharacteristics[i].valueOrDefault(),
                positionCharacteristics[i].valueOrDefault()
            )
        }
        return l.maxOrNull()!!
    }

}