package com.pLg.app.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.pLg.app.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun navigate_from_content_to_user_screen() {
        hiltRule.inject()

        // Verifica se a tela inicial mostra conteúdos
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()

        // Usa Espresso para clicar em botão de navegação
        onView(withText("Perfil")).perform(click())

        // Agora a tela de usuário deve estar visível
        composeRule.onNodeWithText("Alice").assertIsDisplayed()
    }
}
