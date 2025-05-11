package com.example.meditracker.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toLocalDate(): LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ofPattern("dd MMM yyyy"))
}