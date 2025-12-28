package com.example.branchmessaging.ui.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    threadId: Int
) {

    // Temporary static messages
    val messages = listOf(
        ChatMessageUiModel(
            sender = "User",
            body = "Hello"
        ),
        ChatMessageUiModel(
            sender = "Agent",
            body = "Hi, how can I help?"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Thread $threadId") }
            )
        },
        bottomBar = {
            MessageInput()
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(8.dp)
        ) {
            items(messages) { message ->
                ChatMessageItem(message)
            }
        }
    }
}

@Composable
private fun ChatMessageItem(message: ChatMessageUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = message.sender,
            style = MaterialTheme.typography.labelSmall
        )

        Text(
            text = message.body,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun MessageInput() {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.weight(1f),
            placeholder = { Text("Type a message") }
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = {
                // send logic later
                text = ""
            }
        ) {
            Text("Send")
        }
    }
}

data class ChatMessageUiModel(
    val sender: String,
    val body: String
)
