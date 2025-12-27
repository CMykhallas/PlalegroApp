package org.playlearn.ui.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.playlearn.core.model.ContentStatus

@Composable
fun ContentScreen(
    viewModel: ContentViewModel,
    onProfile: () -> Unit,
    onPrefs: () -> Unit
) {
    val contents by viewModel.contents.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Conteúdos disponíveis", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        contents.forEach { content ->
            Row(
                Modifier.fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .testTag("content_${content.id}")
            ) {
                Text(
                    content.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                val statusText = if (content.status == ContentStatus.AVAILABLE) "Disponível" else "Bloqueado"
                Text(statusText, style = MaterialTheme.typography.bodyMedium)
            }
        }
        Spacer(Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = onProfile) { Text("Perfil") }
            Button(onClick = onPrefs) { Text("Preferências") }
        }
        Spacer(Modifier.height(12.dp))
        OutlinedButton(onClick = { viewModel.refresh() }) { Text("Atualizar conteúdos") }
    }
}
