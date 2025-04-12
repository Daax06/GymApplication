package com.application.intelligym.screens

sealed class Screen(val route: String) {
    object Starting : Screen("starting")
    object GymLanding : Screen("gymLanding")
    object Login : Screen("login")
    object CreateAccount : Screen("createAccount")
    object Dashboard : Screen("dashboard")
    object WorkoutUpper : Screen("workoutUpper")
    object WorkoutLower : Screen("workoutLower")
    object WorkoutCore : Screen("workoutCore")
    object Calendar : Screen("calendar")
}
