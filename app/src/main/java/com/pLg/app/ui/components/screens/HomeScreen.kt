package com.pLg.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pLg.app.R
import com.pLg.app.ui.components.AppTopBar

@Composable
fun HomeScreen(
    windowSizeClass: WindowSizeClass,
    onNavigateSettings: () -> Unit,
    onNavigateAbout: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTopBar(titleRes = R.string.title_home)

        Spacer(modifier = Modifier.height(16.dp))

        Card {
            Column(
                modifier = Modifier.padding(PaddingValues(16.dp)),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = if (state.isOnline) {
                        "Conectado à internet"
                    } else {
                        "Sem conexão"
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
                Button(onClick = { viewModel.refreshConnectivity() }) {
                    Text("Atualizar status de conexão")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onNavigateSettings) {
            Text(text = "Configurações")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = onNavigateAbout) {
            Text(text = "Sobre")
        }
    }
}
