package com.example.meditracker.utils

import com.example.meditracker.domain.model.Analysis
import com.example.meditracker.ui.screens.main.analysis.AnalysisUiState

fun List<Analysis>.toAnalyzesUiStateWithSort(): List<AnalysisUiState> {
    return this.sortedByDescending { it.date.toLocalDate() }.map { it.toAnalysisUiState() }
}