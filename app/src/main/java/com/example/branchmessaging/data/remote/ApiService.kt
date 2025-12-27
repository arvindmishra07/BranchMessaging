package com.example.branchmessaging.data.remote

import com.example.branchmessaging.data.model.LoginResponse
import com.example.branchmessaging.data.model.MessageDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    suspend fun login(
        @Body request: Map<String, String>
    ): LoginResponse

    @GET("api/messages")
    suspend fun getMessages(): List<MessageDto>

    @POST("api/messages")
    suspend fun sendMessage(
        @Body body: Map<String, Any>
    ): MessageDto
}
