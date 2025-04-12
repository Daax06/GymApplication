package com.application.intelligym.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.application.intelligym.screens.DashboardScreen
import com.application.intelligym.screens.LoginScreen
import com.application.intelligym.viewmodels.AuthViewModel
import com.application.intelligym.screens.RegistrationScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            DashboardScreen()
        }
    }
}

@Composable
fun AppNavGraph(navController: NavHostController, viewModel: AuthViewModel) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, viewModel) }
        composable("register") { RegistrationScreen(navController, viewModel) }
        // Add other destinations here
    }
}