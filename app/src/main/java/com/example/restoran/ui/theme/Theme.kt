package com.example.restoran.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun SushiinTheme(
    isDarkMode: Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkMode) {
        darkColorScheme(
            primary = Color(0xFFFF5252), // Red sushi theme
            background = Color(0xFF1A1A1A),
            surface = Color(0xFF2D2D2D)
        )
    } else {
        lightColorScheme(
            primary = Color(0xFFD32F2F), // Sushi red
            background = Color(0xFFFCFCFC),
            surface = Color(0xFFF5F5F5)
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
