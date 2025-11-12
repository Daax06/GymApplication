package com.application.intelligym.data.authentication

// LoginUiEvent.kt
sealed class AuthUIEvent {
    data class EmailChanged(val email: String) : AuthUIEvent()
    data class PasswordChanged(val password: String) : AuthUIEvent()
    object Login : AuthUIEvent()
}
