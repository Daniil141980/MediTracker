package com.example.meditracker.utils

import com.example.meditracker.domain.model.Appointment
import com.example.meditracker.ui.screens.main.appointments.AppointmentUIState

fun List<Appointment>.toAppointmentUiState(): List<AppointmentUIState> {
    return this.map { it.toAppointmentUiState() }
}