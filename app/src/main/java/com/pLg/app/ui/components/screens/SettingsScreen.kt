package com.pLg.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pLg.app.R
import com.pLg.app.ui.components.AppTopBar

@Composable
fun SettingsScreen(
    windowSizeClass: WindowSizeClass,
    onBack: () -> Unit
) {
    var darkMode by remember { mutableStateOf(false) }
    var soundsEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        AppTopBar(titleRes = R.string.title_settings, onBack = onBack)

        Spacer(modifier = Modifier.height(16.dp))

        RowSetting(
            label = "Modo escuro",
            checked = darkMode,
            onCheckedChange = { darkMode = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        RowSetting(
            label = "Sons habilitados",
            checked = soundsEnabled,
            onCheckedChange = { soundsEnabled = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onBack) {
            Text("Salvar e voltar")
        }
    }
}

@Composable
private fun RowSetting(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    androidx.compose.foundation.layout.Row(
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize().padding(vertical = 4.dp)
    ) {
        Text(text = label)
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
