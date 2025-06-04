package com.example.meditracker.utils

import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun String.toLocalTime(): LocalTime{
    return LocalTime.parse(this, DateTimeFormatter.ofPattern("HH:mm"))
}