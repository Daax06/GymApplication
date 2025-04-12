package com.application.intelligym

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.application.intelligym.theme.IntelliGymTheme
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this) // Initialize Firebase

        setContent {
            IntelliGymTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    com.application.intelligym.screens.LoginScreen() // Launch the full app
                }
            }
        }
    }
}