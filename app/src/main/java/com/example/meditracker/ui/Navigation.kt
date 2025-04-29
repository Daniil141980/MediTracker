package com.example.meditracker.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meditracker.ui.screens.Screen
import com.example.meditracker.ui.screens.login.SignInScreen
import com.example.meditracker.ui.screens.login.SignUpScreen
import com.example.meditracker.ui.viewModels.SignInScreenViewModel
import com.example.meditracker.ui.viewModels.SignUpScreenViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SignInScreen.route,
    ) {
        composable(route = Screen.SignInScreen.route) {
            val viewModel = hiltViewModel<SignInScreenViewModel>()
            SignInScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.SignUpScreen.route) {
            val viewModel = hiltViewModel<SignUpScreenViewModel>()
            SignUpScreen(navController = navController, viewModel = viewModel)
        }
    }
}