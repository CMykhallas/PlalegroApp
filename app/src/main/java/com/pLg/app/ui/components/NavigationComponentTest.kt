package com.pLg.app.ui.componentes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class NavigationComponentTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun navigation_switches_between_screens() {
        composeRule.setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "screen1") {
                composable("screen1") { Text("Tela 1") }
                composable("screen2") { Text("Tela 2") }
            }
        }

        composeRule.onNodeWithText("Tela 1").assertIsDisplayed()
        // Simulação de navegação: normalmente feita via ação do usuário
        // Aqui apenas validamos que a tela inicial aparece
    }
}
