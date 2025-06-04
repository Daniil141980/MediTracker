package com.example.meditracker.ui.screens.main.appointments

data class AppointmentUIState(
    val doctorSpecialty: String = "",
    val date: String = "",
    val time: String = "",
    val isVisited: Boolean = false,
)