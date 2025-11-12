package com.application.intelligym.screens.planner

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import com.application.intelligym.components.*
import com.application.intelligym.data.workoutplanner.CalendarUtils
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import androidx.compose.ui.graphics.graphicsLayer // <-- Add this import

@Composable
fun WorkoutCalendarScreen() {
    val currentMonth = remember { mutableStateOf(LocalDate.now().month) }
    val currentYear = remember { mutableIntStateOf(LocalDate.now().year) }
    val selectedDate = remember { mutableStateOf(LocalDate.now()) }

    val daysInMonth = CalendarUtils.daysInMonthArray(selectedDate.value)
    val currentDay = LocalDate.now().dayOfMonth // Get the current day of the month



    Box(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen
            .padding(18.dp)
            .statusBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth() // Ensure it takes up full width
                .wrapContentHeight() // Will only take as much height as its content
        ) {
            // Calendar Container: Combine both header and calendar grid in one container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Black, shape = RoundedCornerShape(10.dp))
                    .padding(5.dp)
                    .offset(y = 1.dp) // Moves the container 16 dp down from the top
            ) {
                Column {
                    // Calendar Header (Month, Year, Navigation)
                    CalendarHeader(
                        currentMonth = currentMonth.value.getDisplayName(TextStyle.FULL, Locale.getDefault()),
                        currentYear = currentYear.intValue,
                        onPreviousMonth = {
                            selectedDate.value = selectedDate.value.minusMonths(1)
                            currentMonth.value = selectedDate.value.month
                            currentYear.intValue = selectedDate.value.year
                        },
                        onNextMonth = {
                            selectedDate.value = selectedDate.value.plusMonths(1)
                            currentMonth.value = selectedDate.value.month
                            currentYear.intValue = selectedDate.value.year
                        }
                    )

                    // Divider between header and calendar grid
                    Divider(modifier = Modifier.padding(vertical = 2.dp), color = Color.Gray)

                    // Calendar Grid
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(7),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Days of Week Headers
                        items(listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")) { dayOfWeek ->
                            Text(
                                text = dayOfWeek,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp
                            )
                        }

                        // Days in Month
                        items(daysInMonth) { date ->
                            val isCurrentMonth = date.month == selectedDate.value.month
                            val isToday = date.dayOfMonth == currentDay

                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .background(
                                        if (isToday) Color.Black else if (isCurrentMonth) Color.Gray else Color.Gray.copy(alpha = 0.6f), // Black for today
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .clickable { /* Handle click for a valid day */ }
                                    .aspectRatio(1f) // Makes it square
                                    .graphicsLayer { alpha = if (isCurrentMonth) 1f else 0.6f }, // Apply opacity to the whole Box
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = date.dayOfMonth.toString(),
                                    color = if (isToday) Color.White else if (isCurrentMonth) Color.Black else Color.White.copy(alpha = 0.6f), // White for today and black for others
                                    textAlign = TextAlign.Center,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }

            // "Add Workout" Placeholder Below the Calendar Container
            Spacer(modifier = Modifier.height(16.dp)) // Add some space before the placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(12.dp))
                    .clickable { /* Handle click for adding a workout */ }
                    .height(50.dp), // Height of the placeholder
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add Workout",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        }

        // "Save Routine" Button at the Very Bottom of the Screen
        Button(
            onClick = {
                // Handle save routine logic here (for now, display a message)
                println("Routine Saved!")
            },
            modifier = Modifier
                .align(Alignment.BottomCenter) // Align the button at the bottom
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Add padding on the sides for the button
                .padding(bottom = 16.dp) // Ensure it's not flush with the bottom of the screen
        ) {
            Text("Save Routine")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorkoutCalendarScreen() {
    WorkoutCalendarScreen()
}


