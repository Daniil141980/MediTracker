package com.example.meditracker.ui.screens.main.addDiaryEntry

import com.example.meditracker.R
import com.example.meditracker.domain.model.DiaryEntry
import com.example.meditracker.utils.toFormattedDate
import java.time.LocalDate

data class AddDiaryEntryScreenUiState(
    val heading: String = "",
    val description: String = "",
    val pickedDate: LocalDate = LocalDate.now(),
    val formattedDate: String = LocalDate.now().toFormattedDate(),
    val headingErrorMessage: Int? = R.string.empty_field,
    val descriptionErrorMessage: Int? = R.string.empty_field,
) {
    fun toDiaryEntry(): DiaryEntry {
        return DiaryEntry(
            heading,
            description,
            formattedDate,
        )
    }
}