package com.example.meditracker.ui.screens.main.changePassword

import com.example.meditracker.R

data class ChangePasswordScreenUiState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val newPasswordAgain: String = "",
    val oldPasswordErrorMessage: Int? = R.string.empty_field,
    val newPasswordErrorMessage: Int? = R.string.empty_field,
    val newPasswordAgainErrorMessage: Int? = R.string.empty_field,
)