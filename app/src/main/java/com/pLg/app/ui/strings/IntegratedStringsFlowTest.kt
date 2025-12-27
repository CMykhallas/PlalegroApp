package com.pLg.app.ui.strings

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.ui.content.ContentScreen
import com.pLg.app.ui.viewmodel.ContentViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class IntegratedStringsFlowTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun full_flow_strings_display_in_ui() {
        val fakeVM = object : ContentViewModel(FakeContentRepo()) {}
        fakeVM.contents = MutableStateFlow(listOf(
            com.pLg.core.model.Content("c1", "Matemática Básica", 1, "AVAILABLE", emptyMap())
        ))

        composeRule.setContent {
            ContentScreen(viewModel = fakeVM, onProfile = {}, onPrefs = {})
        }

        // Verifica se strings aparecem corretamente na UI
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
        composeRule.onNodeWithText("Perfil").assertIsDisplayed()
        composeRule.onNodeWithText("Preferências").assertIsDisplayed()
    }
}
