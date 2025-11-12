package com.application.intelligym

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.application.intelligym.app.IntelliGymApp
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        FirebaseApp.initializeApp(this)

        setContent {
            IntelliGymApp()
        }
    }
}

@Preview
@Composable
fun DefaultPreview(){
    IntelliGymApp()
}