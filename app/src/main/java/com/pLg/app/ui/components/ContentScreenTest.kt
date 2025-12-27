package com.pLg.app.ui.components.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.ui.content.ContentScreen
import com.pLg.app.ui.viewmodel.ContentViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class ContentScreenTest {
    @get:Rule val composeRule = createComposeRule()

    @Test
    fun content_screen_displays_items() {
        val fakeVM = object : ContentViewModel(FakeContentRepo()) {}
        fakeVM.contents = MutableStateFlow(listOf(
            com.pLg.core.model.Content("c1", "Matemática Básica", 1, "AVAILABLE", emptyMap())
        ))

        composeRule.setContent {
            ContentScreen(viewModel = fakeVM, onProfile = {}, onPrefs = {})
        }

        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
    }
}
