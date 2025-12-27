package org.playlearn.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(viewModel: UserViewModel) {
    val user by viewModel.user.collectAsState()
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = user?.name ?: "—")
        Spacer(Modifier.height(8.dp))
        Text(text = user?.age?.let { "$it anos" } ?: "—")
    }
}
