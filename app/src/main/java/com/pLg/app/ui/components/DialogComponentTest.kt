package com.pLg.app.ui.componentes

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import org.junit.Rule
import org.junit.Test

class DialogComponentTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun dialog_shows_and_dismisses() {
        var open = true

        composeRule.setContent {
            if (open) {
                AlertDialog(
                    onDismissRequest = { open = false },
                    confirmButton = {
                        TextButton(onClick = { open = false }) { Text("OK") }
                    },
                    title = { Text("Título") },
                    text = { Text("Mensagem do diálogo") }
                )
            }
        }

        composeRule.onNodeWithText("Mensagem do diálogo").assertIsDisplayed()
        composeRule.onNodeWithText("OK").performClick()
    }
}
