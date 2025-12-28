package com.example.branchmessaging.ui.threads

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThreadsScreen(
    navController: androidx.navigation.NavController,
    viewModel: ThreadsViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Messages") }
            )
        }
    ) { padding ->

        when {
            viewModel.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            viewModel.errorMessage != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = viewModel.errorMessage ?: "",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    items(viewModel.threads) { thread ->
                        ThreadItem(thread) {
                            navController.navigate("chat/${thread.threadId}")
                        }
                    }


                }
            }
        }
    }
}

@Composable
private fun ThreadItem(thread: ThreadUiModel,
                       onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(
            text = "Thread ${thread.threadId}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = thread.lastMessage,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = thread.timestamp,
            style = MaterialTheme.typography.bodySmall
        )
    }

    Divider()
}

@Composable
private fun MessageItem(message: com.example.branchmessaging.data.model.MessageDto) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Thread: ${message.threadId}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = message.body,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = message.timestamp,
            style = MaterialTheme.typography.bodySmall
        )
    }

    Divider()
}
