package com.application.intelligym.screens.appmain

import java.time.LocalDate
import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.application.intelligym.R
import com.application.intelligym.components.AppBarComponent
import com.application.intelligym.data.home.HomeViewModel
import kotlinx.coroutines.launch
import com.application.intelligym.components.*

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(

        topBar = {
            AppBarComponent(
                title = "IntelliGym",
                isLoggedIn = true,
                onLoginClick = { /* nav to Login */ },
                onLogoutClick = {
                    // show feedback then logout
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Logged out successfully")
                    }
                    homeViewModel.logout()
                }
            )

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .statusBarsPadding()
        ) {
            Text(text = "Welcome to IntelliGym!", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(24.dp))  // Add space after welcome text

            // 🏷️ Event Card
            EventCard(
                title = "Events",
                subtitle = "See more details.",
                imageRes = R.drawable.gym_background, // Replace with your image resource
                modifier = Modifier.fillMaxWidth() // Customize the modifier
            )

            Spacer(modifier = Modifier.height(24.dp))  // Add space between EventCard and WeeklyCalendar

            // 📆 Weekly Calendar
            WeeklyCalendar(
                currentDate = LocalDate.now(), // Pass today's date as the current date
                onDateSelected = { selectedDate ->
                    // Handle date selection here if needed
                    Log.d("WeeklyCalendar", "Selected date: $selectedDate")
                }
            )
        }
    }
}
@Preview
@Composable
fun DefaultHomeViewScreen() {
    HomeScreen()
}
