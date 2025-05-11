package com.example.meditracker.ui.screens.main.analysis

data class AnalysisUiState(
    val name: String = "",
    val unit: String = "",
    val result: Double = 0.0,
    val normality: AnalysisNormalityUiState,
)