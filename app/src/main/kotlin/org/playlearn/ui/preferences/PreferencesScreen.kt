package org.playlearn.ui.preferences

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun PreferencesScreen(
    viewModel: PreferencesViewModel,
    onBack: () -> Unit
) {
    val prefs by viewModel.prefs.collectAsState()
    val saving by viewModel.saving.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Idioma atual: ${prefs.locale}")
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = { viewModel.updateLocale("pt-MZ") }) { Text("pt-MZ") }
            OutlinedButton(onClick = { viewModel.updateLocale("en-US") }) { Text("en-US") }
        }
        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Tema escuro: ${prefs.darkTheme}")
            Switch(checked = prefs.darkTheme, onCheckedChange = { viewModel.toggleDarkTheme() })
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { viewModel.save() },
            modifier = Modifier.testTag("save_prefs_button")
        ) {
            Text(if (saving) "Guardando..." else "Guardar")
        }
        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = onBack) { Text("Voltar") }
    }
}
