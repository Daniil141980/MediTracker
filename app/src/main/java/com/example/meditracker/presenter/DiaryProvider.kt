package com.example.meditracker.presenter

import com.example.meditracker.domain.model.DiaryEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object DiaryProvider {

    private val _diaryEntry = MutableStateFlow(DiaryEntry())
    val diaryEntry = _diaryEntry.asStateFlow()

    fun setDiaryEntry(diaryEntry: DiaryEntry) {
        _diaryEntry.value = diaryEntry
    }
}