package com.pLg.app.ui.theme

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test

class TypographyTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun app_theme_applies_typography_styles() {
        composeRule.setContent {
            AppTheme {
                androidx.compose.material3.Text("Título", style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
            }
        }

        composeRule.onNodeWithText("Título").assertIsDisplayed()
    }
}
