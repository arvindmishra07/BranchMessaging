package com.example.branchmessaging.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.branchmessaging.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: MessageRepository
) : ViewModel() {
    var loginSuccess by mutableStateOf(false)
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onEmailChange(value: String) {
        email = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    fun login() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            val result = repository.login(email)

            isLoading = false

            result
                .onSuccess {
                    loginSuccess = true
                }
                .onFailure {
                    errorMessage = "Invalid credentials"
                }

        }
    }
}
