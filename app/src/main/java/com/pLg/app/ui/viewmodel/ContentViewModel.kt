@HiltViewModel
class ContentViewModel @Inject constructor(
    private val repo: ContentRepository
) : ViewModel() {
    val contents: Flow<List<Content>> = repo.observeContent()
}
package com.pLg.app.ui.preferences

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.pLg.app.ui.viewmodel.PreferencesViewModel

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
