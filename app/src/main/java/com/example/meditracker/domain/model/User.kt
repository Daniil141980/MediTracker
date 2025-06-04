package com.example.meditracker.domain.model

data class User(
    val analyzes: List<Analysis> = mutableListOf(),
    val diaryEntries: List<DiaryEntry> = mutableListOf(),
    val appointments: List<Appointment> = mutableListOf(),
)