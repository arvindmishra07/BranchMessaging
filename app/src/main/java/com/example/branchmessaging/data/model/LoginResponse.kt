package com.example.branchmessaging.data.model


import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "auth_token")
    val authToken: String
)
