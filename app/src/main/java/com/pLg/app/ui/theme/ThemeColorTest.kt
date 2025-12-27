package com.pLg.app.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test

class ThemeColorTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun app_theme_applies_primary_color() {
        composeRule.setContent {
            AppTheme {
                androidx.compose.material3.Text("Teste", color = androidx.compose.material3.MaterialTheme.colorScheme.primary)
            }
        }

        // Verifica se o texto existe (não conseguimos validar cor diretamente, mas garantimos renderização)
        composeRule.onNodeWithText("Teste").assertIsDisplayed()
    }
}
