package com.application.intelligym.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.rememberNavController
import com.application.intelligym.navigation.NavGraph
import com.application.intelligym.theme.IntelliGymTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntelliGymTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}