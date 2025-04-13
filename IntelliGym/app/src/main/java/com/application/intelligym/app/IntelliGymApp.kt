package com.application.intelligym.app

import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.application.intelligym.data.home.HomeViewModel
import com.application.intelligym.navigation.PostOfficeAppRouter
import com.application.intelligym.navigation.Screen
import com.application.intelligym.screens.HomeScreen
import com.application.intelligym.screens.LoginScreen
import com.application.intelligym.screens.SignUpScreen
import com.application.intelligym.screens.TermsAndConditionsScreen


@Composable
fun IntelliGymApp(homeViewModel: HomeViewModel = viewModel()) {

    homeViewModel.checkForActiveSession()

    surfaceColorAtElevation(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        if (homeViewModel.isUserLoggedIn.value == true) {
            PostOfficeAppRouter.navigateTo(Screen.HomeScreen)
        }

        Crossfade(targetState = PostOfficeAppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }

                is Screen.TermsAndConditionsScreen -> {
                    TermsAndConditionsScreen()
                }

                is Screen.LoginScreen -> {
                    LoginScreen()
                }

                is Screen.HomeScreen -> {
                    HomeScreen()
                }
            }
        }

    }
}