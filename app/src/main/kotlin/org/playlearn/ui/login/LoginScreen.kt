package org.playlearn.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.playlearn.core.util.AppResult

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    var name by remember { mutableStateOf("") }
    var ageText by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Nome") },
            modifier = Modifier.fillMaxWidth().testTag("username")
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = ageText,
            onValueChange = { ageText = it },
            placeholder = { Text("Idade") },
            modifier = Modifier.fillMaxWidth().testTag("age")
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { ageText.toIntOrNull()?.let { viewModel.login(name, it) } },
            modifier = Modifier.testTag("login_button")
        ) {
            Text("Entrar")
        }

        when (val s = state) {
            is AppResult.Success -> LaunchedEffect("success") { onSuccess() }
            is AppResult.Error -> Text("Erro: ${s.throwable.message}")
            is AppResult.Loading -> LinearProgressIndicator(Modifier.fillMaxWidth())
            null -> {}
        }
    }
}
