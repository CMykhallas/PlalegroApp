package com.pLg.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.pLg.shared.domain.Result
import com.pLg.shared.domain.UseCases

@Composable
fun MainScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Nome") })
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })

        Button(onClick = {
            val result = UseCases.registerUser(name, email)
            message = when (result) {
                is Result.Ok -> "Usuário registrado: ${result.value.name}"
                is Result.Err -> "Erro: ${result.error.message}"
            }
        }) {
            Text("Registrar")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(message)
    }
}
