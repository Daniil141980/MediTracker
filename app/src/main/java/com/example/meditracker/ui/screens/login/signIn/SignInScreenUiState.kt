package com.example.meditracker.ui.screens.login.signIn

import com.example.meditracker.R

data class SignInScreenUiState(
    val email: String = "",
    val password: String = "",
    val emailErrorMessage: Int? = R.string.empty_field,
    val passwordErrorMessage: Int? = R.string.empty_field,
)