package com.pLg.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Purple40,
    secondary = Teal40,
    background = White,
    surface = White
)

private val DarkColors = darkColorScheme(
    primary = Purple80,
    secondary = Teal80
)

@Composable
fun PlayLearnGrowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = PlayLearnGrowTypography,
        content = content
    )
}
