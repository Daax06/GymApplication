package com.application.intelligym.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

data class GymEvent(val title: String, val date: String, val imageUrl: String)

val sampleEvents = listOf(
    GymEvent("Bench Press Challenge", "April 9, 2025", "https://via.placeholder.com/300x150.png?text=Bench+Challenge"),
    GymEvent("Lat Pulldown Competition", "April 10, 2025", "https://via.placeholder.com/300x150.png?text=Lat+Pulldown"),
    GymEvent("Upper Body Strength Day", "April 12, 2025", "https://via.placeholder.com/300x150.png?text=Upper+Body")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Events", color = Color.White) },
                navigationIcon = {
                    Icon(Icons.Filled.FitnessCenter, contentDescription = null, tint = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFDB1F1F)
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            items(sampleEvents) { event ->
                EventCard(event)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun EventCard(event: GymEvent) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column {
            Image(
                painter = rememberImagePainter(data = event.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = event.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = event.date, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun DashboardScreen(navController: NavHostController, viewModel: EventViewModel) {
    val events = viewModel.events.collectAsState()
    Column {
        Text("Events", style = MaterialTheme.typography.h5)
        LazyColumn {
            items(events.value) { event ->
                EventCard(event = event)
            }
        }
    }
}
