package com.mars.ai_lab_7.data.positions

data class CandidateInfo(
    val id: Int,
    val name: String,
    val characteristicsValues: Map<String, Float>
)