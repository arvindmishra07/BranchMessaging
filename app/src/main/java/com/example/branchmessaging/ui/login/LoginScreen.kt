package com.example.branchmessaging.ui.login


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    navController: androidx.navigation.NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    LaunchedEffect(viewModel.loginSuccess) {
        if (viewModel.loginSuccess) {
            navController.navigate("threads") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Agent Login",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !viewModel.isLoading
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = viewModel::onPasswordChange,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            enabled = !viewModel.isLoading
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.login() },
            modifier = Modifier.fillMaxWidth(),
            enabled = !viewModel.isLoading
        ) {
            if (viewModel.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text("Login")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        viewModel.errorMessage?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}



