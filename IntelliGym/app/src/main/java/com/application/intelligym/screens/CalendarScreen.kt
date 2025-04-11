package com.application.intelligym.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme

@Composable
fun CalendarScreen(events: List<WorkoutEvent>) {
    Column {
        Text("Upcoming Events", style = MaterialTheme.typography.h6)
        // Placeholder for a custom calendar
        LazyColumn {
            items(events) { event ->
                EventCard(event)
            }
        }
    }
}
