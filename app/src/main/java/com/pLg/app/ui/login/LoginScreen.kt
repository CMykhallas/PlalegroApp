package com.pLg.app.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.pLg.app.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel, onSuccess: () -> Unit) {
    val state by viewModel.loginState.collectAsState()

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = name, onValueChange = { name = it }, modifier = Modifier.testTag("username"))
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = age, onValueChange = { age = it }, modifier = Modifier.testTag("age"))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.login(name, age.toIntOrNull() ?: 0) }, modifier = Modifier.testTag("login_button")) {
            Text("Login")
        }
    }

    state?.onSuccess { onSuccess() }
}
