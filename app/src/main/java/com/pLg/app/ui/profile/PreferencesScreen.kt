@Composable
fun PreferencesScreen(viewModel: PreferencesViewModel, onBack: () -> Unit) {
    val locale by viewModel.locale.collectAsState()
    Column {
        Text("Idioma atual: $locale")
        Button(onClick = { viewModel.updateLocale("pt-MZ") }, modifier = Modifier.testTag("save_prefs_button")) {
            Text("Salvar Preferências")
        }
        Button(onClick = onBack) { Text("Voltar") }
    }
}
package com.pLg.app.ui.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.pLg.app.ui.viewmodel.ContentViewModel

@Composable
fun ContentScreen(viewModel: ContentViewModel, onProfile: () -> Unit, onPrefs: () -> Unit) {
    val contents by viewModel.contents.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(contents) { item ->
                Text(item.title)
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = onProfile) { Text("Perfil") }
            Button(onClick = onPrefs) { Text("Preferências") }
        }
    }
}
