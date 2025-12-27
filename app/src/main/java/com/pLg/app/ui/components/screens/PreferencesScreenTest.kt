package com.pLg.app.ui.componentes.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.ui.preferences.PreferencesScreen
import com.pLg.app.ui.viewmodel.PreferencesViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class PreferencesScreenTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun preferences_screen_updates_locale() {
        val fakeViewModel = object : PreferencesViewModel(FakeUserRepo()) {}
        fakeViewModel.locale = MutableStateFlow("pt-MZ")

        var backCalled = false

        composeRule.setContent {
            PreferencesScreen(viewModel = fakeViewModel, onBack = { backCalled = true })
        }

        composeRule.onNodeWithText("Idioma atual: pt-MZ").assertIsDisplayed()
        composeRule.onNodeWithTag("save_prefs_button").performClick()
        composeRule.onNodeWithText("Voltar").performClick()

        assert(backCalled)
    }
}
