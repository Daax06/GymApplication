package com.application.intelligym.screens.appmain

import com.application.intelligym.components.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.application.intelligym.data.profile.ProfileViewModel
import com.application.intelligym.components.*

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val user by viewModel.user.collectAsState()

    user?.let {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            item { UserInfoSection(it) }
            item { EditableBio(it.bio, viewModel::updateBio) }
            item { MembershipCard(it.membership) }
            item { WorkoutHistorySection(it.workoutHistory) }
            item { QRCodeSection(FirebaseAuth.getInstance().currentUser?.uid ?: "") }
        }

        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(16.dp)
                .statusBarsPadding()
        ) {
            // Title
            Text(text = "Edit Profile", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.Companion.height(16.dp))

            // Name Field
            ProfileInputField(
                label = "Full Name",
                placeholder = "Enter your full name"
            )

            Spacer(modifier = Modifier.Companion.height(16.dp))

            // Email Field
            ProfileInputField(
                label = "Email Address",
                placeholder = "Enter your email"
            )

            Spacer(modifier = Modifier.Companion.height(16.dp))

            // Phone Number Field
            ProfileInputField(
                label = "Phone Number",
                placeholder = "Enter your phone number"
            )

            Spacer(modifier = Modifier.Companion.height(16.dp))

            // Save Button
            Button(
                onClick = { /* Handle save functionality */ },
                modifier = Modifier.Companion.fillMaxWidth()
            ) {
                Text(text = "Save Changes")
            }
        }

    } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}