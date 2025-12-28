package com.example.branchmessaging.ui.threads


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.branchmessaging.data.model.MessageDto
import com.example.branchmessaging.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThreadsViewModel @Inject constructor(
    private val repository: MessageRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var threads by mutableStateOf<List<ThreadUiModel>>(emptyList())
        private set


    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchMessages()
    }

    fun fetchMessages() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val messages = repository.getAllMessages()

                threads = messages
                    .groupBy { it.threadId }
                    .map { (threadId, msgs) ->
                        val lastMsg = msgs.maxBy { it.timestamp }
                        ThreadUiModel(
                            threadId = threadId,
                            lastMessage = lastMsg.body,
                            timestamp = lastMsg.timestamp
                        )
                    }
                    .sortedByDescending { it.timestamp }

            } catch (e: Exception) {
                errorMessage = "Failed to load messages"
            } finally {
                isLoading = false
            }
        }
    }
}
