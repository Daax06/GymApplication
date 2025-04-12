package com.application.intelligym

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.application.intelligym.*
import com.application.intelligym.screens.Screen

@Composable
fun IntelliGymApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Starting.route) {
        composable(Screen.Starting.route) { StartingScreen(navController) }
        composable(Screen.GymLanding.route) { GymLandingScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.CreateAccount.route) { CreateAccountScreen(navController) }
        composable(Screen.Dashboard.route) { DashboardScreen(navController) }
        composable(Screen.WorkoutUpper.route) { WorkoutUpperScreen(navController) }
        composable(Screen.WorkoutLower.route) { WorkoutLowerScreen(navController) }
        composable(Screen.WorkoutCore.route) { WorkoutCoreScreen(navController) }
        composable(Screen.Calendar.route) { CalendarScreen(navController) }
    }
}