package com.pLg.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pLg.app.R
import com.pLg.app.ui.components.AppTopBar

@Composable
fun AboutScreen(
    windowSizeClass: WindowSizeClass,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        AppTopBar(titleRes = R.string.title_about, onBack = onBack)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Play Learn Grow")
        Text("Versão educacional com foco em inclusão e acessibilidade.")
        Text("Desenvolvido com Kotlin, Jetpack Compose e Hilt.")
    }
}
