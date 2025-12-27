package com.pLg.app.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.core.model.Content
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class ContentScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun content_list_displays_items() {
        val fakeContent = listOf(
            Content("id1", "Matemática Básica", 1, "AVAILABLE", emptyMap()),
            Content("id2", "Ciências Naturais", 1, "LOCKED", emptyMap())
        )

        composeTestRule.setContent {
            ContentScreen(contents = flowOf(fakeContent))
        }

        composeTestRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ciências Naturais").assertIsDisplayed()
    }
}

@Composable
fun ContentScreen(contents: kotlinx.coroutines.flow.Flow<List<Content>>) {
    val list by contents.collectAsState(initial = emptyList())
    LazyColumn {
        items(list.size) { index ->
            BasicText(list[index].title)
        }
    }
}
