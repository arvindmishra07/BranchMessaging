package com.example.branchmessaging.data.model

import com.squareup.moshi.Json

data class MessageDto(
    val id: Int,

    @Json(name = "thread_id")
    val threadId: Int,

    @Json(name = "user_id")
    val userId: Int,

    @Json(name = "agent_id")
    val agentId: Int?,

    val body: String,

    val timestamp: String
)
