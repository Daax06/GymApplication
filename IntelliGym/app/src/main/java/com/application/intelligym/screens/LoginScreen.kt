package com.application.intelligym.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

private

@Composable
fun LoginScreen(navController: NavHostController, viewModel: AuthViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Log In", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        val email = null
        OutlinedTextField(value = email, onValueChange = { var email = it }, label = { Text("Email") })
        OutlinedTextField(value = password, onValueChange = { var password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())
        Button(onClick = { viewModel.login(email, password) }) {
            Text("Log In")
        }
        TextButton(onClick = { /* Google login logic */ }) {
            Text("Continue with Google")
        }
    }
}
