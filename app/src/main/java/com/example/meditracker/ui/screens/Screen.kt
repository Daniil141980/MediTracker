package com.example.meditracker.ui.screens

sealed class Screen(val route: String) {

    object SignInScreen : Screen("sign_in_screen")
    object SignUpScreen : Screen("sign_up_screen")
    object AnalysisScreen : Screen("analysis_screen")

}