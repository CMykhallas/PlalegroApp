package com.pLg.app.ui.componentes.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.ui.login.LoginScreen
import com.pLg.app.ui.content.ContentScreen
import com.pLg.app.ui.profile.ProfileScreen
import com.pLg.app.ui.preferences.PreferencesScreen
import com.pLg.app.ui.viewmodel.*
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class IntegratedScreensFlowTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun full_flow_login_content_profile_preferences() {
        val userRepo = FakeUserRepo()
        val contentRepo = FakeContentRepo()

        val loginVM = LoginViewModel(userRepo, androidx.lifecycle.SavedStateHandle())
        val contentVM = ContentViewModel(contentRepo)
        val userVM = UserViewModel(userRepo, androidx.lifecycle.SavedStateHandle())
        val prefsVM = PreferencesViewModel(userRepo)

        var currentScreen = "login"

        composeRule.setContent {
            when (currentScreen) {
                "login" -> LoginScreen(viewModel = loginVM, onSuccess = { currentScreen = "content" })
                "content" -> ContentScreen(viewModel = contentVM,
                    onProfile = { currentScreen = "profile" },
                    onPrefs = { currentScreen = "preferences" })
                "profile" -> ProfileScreen(viewModel = userVM)
                "preferences" -> PreferencesScreen(viewModel = prefsVM, onBack = { currentScreen = "content" })
            }
        }

        // Login
        composeRule.onNodeWithTag("username").performTextInput("Alice")
        composeRule.onNodeWithTag("age").performTextInput("7")
        composeRule.onNodeWithTag("login_button").performClick()
        loginVM.login("Alice", 7)

        // Conteúdo
        contentRepo.refreshContentPacks()
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()

        // Perfil
        currentScreen = "profile"
        composeRule.waitForIdle()
        userRepo.register(com.pLg.core.model.User("u1", "Alice", 7, "pt-MZ"))
        composeRule.onNodeWithText("Alice").assertIsDisplayed()
        composeRule.onNodeWithText("7 anos").assertIsDisplayed()

        // Preferências
        currentScreen = "preferences"
        composeRule.waitForIdle()
        composeRule.onNodeWithText("Idioma atual: pt-MZ").assertIsDisplayed()
        composeRule.onNodeWithTag("save_prefs_button").performClick()

        // Voltar para conteúdo
        currentScreen = "content"
        composeRule.waitForIdle()
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
    }
}
