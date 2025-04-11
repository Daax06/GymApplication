package com.application.intelligym.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun LandingScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Welcome to IntelliGym", style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        LazyRow {
            itemsimagesList) { image ->
                Image(painter = painterResource(id = image), contentDescription = null, modifier = Modifier.size(120.dp))
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = { navController.navigate("login") }) {
            Text("Sign in with Email")
        }
        OutlinedButton(onClick = { navController.navigate("register") }) {
            Text("Create Account")
        }
    }
}
