package com.application.intelligym

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.application.intelligym.navigation.NavGraph
import com.application.intelligym.theme.IntelliGymTheme

class IntroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntelliGymTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavGraph(
                        navController = TODO()
                    )
                }
            }
        }
    }
}