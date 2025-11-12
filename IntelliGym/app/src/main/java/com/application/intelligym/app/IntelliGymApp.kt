package com.application.intelligym.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.application.intelligym.data.home.HomeViewModel
import com.application.intelligym.navigation.IntelliGymAppRouter
import com.application.intelligym.navigation.Screen
import com.application.intelligym.screens.appmain.HomeScreen
import com.application.intelligym.screens.login.LoginScreen
import com.application.intelligym.screens.login.OTPScreen
import com.application.intelligym.screens.SignUpScreen
import com.application.intelligym.screens.TermsAndConditionsScreen
@Composable
fun IntelliGymApp(homeViewModel: HomeViewModel = viewModel()) {

    homeViewModel.checkForActiveSession()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        // ⬇️ MOVE navigation into LaunchedEffect to avoid recomposition side effects
        LaunchedEffect(homeViewModel.isUserLoggedIn.value) {
            if (homeViewModel.isUserLoggedIn.value == true) {
                IntelliGymAppRouter.navigateTo(Screen.HomeScreen)
            } else {
                IntelliGymAppRouter.navigateTo(Screen.LoginScreen)
            }
        }

        Crossfade(targetState = IntelliGymAppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> SignUpScreen()
                is Screen.TermsAndConditionsScreen -> TermsAndConditionsScreen()
                is Screen.LoginScreen -> LoginScreen()
                is Screen.HomeScreen -> HomeScreen()
                is Screen.OTPScreen -> OTPScreen()

            }
        }
    }
}
