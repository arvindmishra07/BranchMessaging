package com.example.branchmessaging.ui.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.branchmessaging.data.model.MessageDto
import com.example.branchmessaging.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.plus

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: MessageRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val threadId: Int =
        savedStateHandle["threadId"] ?: error("threadId missing")

    var isLoading by mutableStateOf(false)
        private set

    var messages by mutableStateOf<List<MessageDto>>(emptyList())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        loadMessages()
    }
    fun sendMessage(text: String) {
        if (text.isBlank()) return

        // Optimistic UI update
        val tempMessage = com.example.branchmessaging.data.model.MessageDto(
            id = -1,
            threadId = threadId,
            userId = 0,
            agentId = 1,
            body = text,
            timestamp = System.currentTimeMillis().toString()
        )

        messages = messages + tempMessage

        viewModelScope.launch {
            try {
                repository.sendMessage(
                    threadId = threadId,
                    body = text
                )
            } catch (e: Exception) {
                errorMessage = "Failed to send message"
            }
        }
    }
    private fun loadMessages() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val allMessages = repository.getAllMessages()
                messages = allMessages.filter { it.threadId == threadId }
            } catch (e: Exception) {
                errorMessage = "Failed to load conversation"
            } finally {
                isLoading = false
            }
        }
    }
}

