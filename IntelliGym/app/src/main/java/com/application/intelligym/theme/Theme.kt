package com.application.intelligym.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Define the colors as per your theme
val BackgroundColor = Color(0xFF000000) // Black background
val AccentYellowColor = Color(0xFFFFC107) // Yellow accents
val SecondaryGrayColor = Color(0xFF757575) // Gray for secondary elements
val WhiteTextColor = Color(0xFFFFFFFF) // White text
val LightGrayColor = Color(0xFFBDBDBD) // Light gray

// Dark color scheme
private val DarkColorPalette = darkColorScheme(
    primary = AccentYellowColor,     // Yellow for accents
    secondary = SecondaryGrayColor,  // Gray secondary elements
    background = BackgroundColor,    // Black background
    surface = BackgroundColor,       // Black surfaces (cards, dialogs)
    onPrimary = WhiteTextColor,      // White text/icons on yellow
    onSecondary = WhiteTextColor,    // White text/icons on gray
    onBackground = WhiteTextColor,   // White text on black background
    onSurface = WhiteTextColor,      // White text on black surface
    error = Color.Red                // Red for errors (optional)
)

// Light color scheme
private val LightColorPalette = lightColorScheme(
    primary = AccentYellowColor,     // Yellow for accents
    secondary = SecondaryGrayColor,  // Gray secondary elements
    background = WhiteTextColor,     // White background
    surface = WhiteTextColor,        // White surfaces
    onPrimary = BackgroundColor,     // Black text/icons on yellow
    onSecondary = BackgroundColor,   // Black text/icons on gray
    onBackground = BackgroundColor,  // Black text on white background
    onSurface = BackgroundColor,     // Black text on white surface
    error = Color.Red                // Red for errors (optional)
)

@Composable
fun IntelliGymFlow(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Add your typography settings here
        shapes = componentShapes, // Define your component shapes
        content = content
    )
}
