package com.application.intelligym.screens

@Composable
fun WorkoutScreen(category: String) {
    val workouts = getWorkoutByCategory(category)
    LazyColumn {
        items(workouts) { workout ->
            WorkoutCard(workout)
        }
    }
}
