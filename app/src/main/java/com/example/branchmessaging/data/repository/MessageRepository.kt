package com.example.branchmessaging.data.repository

import com.example.branchmessaging.data.model.MessageDto
import com.example.branchmessaging.data.remote.ApiService
import com.example.branchmessaging.data.session.SessionManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepository @Inject constructor(
    private val apiService: ApiService,
    private val sessionManager: SessionManager
) {

    suspend fun login(
        email: String
    ): Result<Unit> {
        return try {
            val response = apiService.login(
                mapOf(
                    "username" to email,
                    "password" to email.reversed()
                )
            )
            sessionManager.saveAuthToken(response.authToken)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllMessages(): List<MessageDto> {
        return apiService.getMessages()
    }

    suspend fun sendMessage(
        threadId: Int,
        body: String
    ): MessageDto {
        return apiService.sendMessage(
            mapOf(
                "thread_id" to threadId,
                "body" to body
            )
        )
    }
}
