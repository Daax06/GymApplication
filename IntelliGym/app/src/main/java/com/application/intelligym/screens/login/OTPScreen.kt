@file:OptIn(ExperimentalComposeUiApi::class)

package com.application.intelligym.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.application.intelligym.navigation.IntelliGymAppRouter
import com.application.intelligym.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun OTPScreen(
    onVerificationSuccess: () -> Unit = {},
    onVerificationFailure: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    phoneNumber: String = "(+63) 09603401679",
    email: String = "kokeshi.jm@gmail.com"
) {
    val hardcodedPasscode = "257169"
    var otpValue by remember { mutableStateOf("") }
    var isVerifying by remember { mutableStateOf(false) }
    var verificationResult by remember { mutableStateOf<String?>(null) }
    var countdown by remember { mutableStateOf(60) }
    var canResend by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    // Countdown timer
    LaunchedEffect(Unit) {
        while (countdown > 0) {
            delay(1000)
            countdown--
        }
        canResend = true
    }

    // Auto-verify when 6 digits are entered
    LaunchedEffect(otpValue) {
        if (otpValue.length == 6) {
            isVerifying = true
            delay(1000) // Simulate network delay
            if (otpValue == hardcodedPasscode) {
                verificationResult = "Verification successful!"
                onVerificationSuccess()
                delay(1500)
                IntelliGymAppRouter.navigateTo(Screen.HomeScreen)
            } else {
                verificationResult = "Invalid passcode. Please try again."
                onVerificationFailure()
            }
            isVerifying = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Title
        Text(
            text = "Verify Your Account",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Subtitle
        Text(
            text = "We've sent a 6-digit code to",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )

        // Phone number and email
        Text(
            text = phoneNumber,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "and $email",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(40.dp))

        // OTP Input Fields
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(6) { index ->
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = when {
                                otpValue.getOrNull(index) != null -> MaterialTheme.colorScheme.primary
                                index == otpValue.length -> MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                                else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                            },
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = otpValue.getOrNull(index)?.toString() ?: "",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    // Cursor indicator
                    if (index == otpValue.length && otpValue.getOrNull(index) == null) {
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(24.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(1.dp)
                                )
                        )
                    }
                }
            }
        }

        // Invisible text field for input handling - moved to avoid BringIntoViewRequester issues
        Box(modifier = Modifier.size(1.dp)) {
            BasicTextField(
                value = otpValue,
                onValueChange = { newValue ->
                    if (newValue.length <= 6 && newValue.all { it.isDigit() }) {
                        otpValue = newValue
                        verificationResult = null
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Verification status
        when {
            isVerifying -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Verifying...",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            verificationResult != null -> {
                Text(
                    text = verificationResult!!,
                    fontSize = 14.sp,
                    color = if (verificationResult!!.contains("successful")) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.error
                    },
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Resend code section
        if (canResend) {
            TextButton(
                onClick = {
                    // Reset timer and allow resend
                    countdown = 60
                    canResend = false
                    otpValue = ""
                    verificationResult = null
                }
            ) {
                Text(
                    text = "Resend Code",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            Text(
                text = "Resend code in ${countdown}s",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Help text
        Text(
            text = "Didn't receive the code? Check your spam folder or try again.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}