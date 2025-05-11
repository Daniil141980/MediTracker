package com.example.meditracker.ui.screens.main.addAnalysis

import com.example.meditracker.R
import com.example.meditracker.domain.model.Analysis
import com.example.meditracker.utils.toFormattedDate
import java.time.LocalDate

data class AddAnalysisScreenUiState(
    val name: String = "",
    val unit: String = "",
    val result: String = "",
    val lowerLimit: String = "",
    val upperLimit: String = "",
    val pickedDate: LocalDate = LocalDate.now(),
    val formattedDate: String = LocalDate.now().toFormattedDate(),
    val nameErrorMessage: Int? = R.string.empty_field,
    val unitErrorMessage: Int? = R.string.empty_field,
    val resultErrorMessage: Int? = R.string.empty_field,
    val lowerLimitErrorMessage: Int? = R.string.empty_field,
    val upperLimitErrorMessage: Int? = R.string.empty_field,
) {
    fun toAnalysis(): Analysis {
        return Analysis(
            name,
            unit,
            result.toDouble(),
            lowerLimit.toDouble(),
            upperLimit.toDouble(),
            formattedDate,
        )
    }
}

