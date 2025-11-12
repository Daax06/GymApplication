package com.application.intelligym.screens.appmain

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsOption(title: String, description: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = description, style = MaterialTheme.typography.bodyMedium)
    }
}

// Placeholder for SettingsHeader
@Composable
fun SettingsHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(vertical = 16.dp)
    )
}

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Apply background color from theme
            .padding(16.dp)
    ) {
        // Settings Screen Header
        SettingsHeader(title = "Settings")

        Spacer(modifier = Modifier.height(16.dp))

        // Settings options (Profile, Privacy, Notifications, etc.)
        SettingsOption(
            title = "Profile",
            description = "Edit your personal information"
        ) {
            // TODO: Add navigation to Profile Screen
        }

        Spacer(modifier = Modifier.height(16.dp))

        SettingsOption(
            title = "Privacy",
            description = "Manage your privacy settings"
        ) {
            // TODO: Add Privacy settings logic
        }

        Spacer(modifier = Modifier.height(16.dp))

        SettingsOption(
            title = "Notifications",
            description = "Set your notification preferences"
        ) {
            // TODO: Add Notifications settings logic
        }

        Spacer(modifier = Modifier.height(16.dp))

        SettingsOption(
            title = "Security",
            description = "Manage your security settings"
        ) {
            // TODO: Add Security settings logic
        }

        Spacer(modifier = Modifier.height(16.dp))

        SettingsOption(
            title = "App Preferences",
            description = "Customize your app experience"
        ) {
            // TODO: Add App Preferences logic
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Logout Button
        Button(
            onClick = {
                // Handle the logout logic here
                // For example, navigate to Login screen or clear session
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp) // Adjust the height of the button
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red // Set button color to red
            ),
            shape = RoundedCornerShape(15.dp) // Set corner radius to 15dp
        ) {
            Text(
                text = "Logout",
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen()
}
