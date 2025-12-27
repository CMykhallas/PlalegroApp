package com.pLg.app.ui.theme

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.ui.theme.AppTheme
import com.pLg.app.ui.content.ContentScreen
import com.pLg.app.ui.viewmodel.ContentViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class IntegratedThemeFlowTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun full_flow_theme_applies_to_content_screen() {
        val fakeVM = object : ContentViewModel(FakeContentRepo()) {}
        fakeVM.contents = MutableStateFlow(listOf(
            com.pLg.core.model.Content("c1", "Matemática Básica", 1, "AVAILABLE", emptyMap())
        ))

        composeRule.setContent {
            AppTheme {
                ContentScreen(viewModel = fakeVM, onProfile = {}, onPrefs = {})
            }
        }

        // Verifica se item aparece com tema aplicado
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
    }
}
