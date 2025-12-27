package com.example.branchmessaging.data.session

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenProvider @Inject constructor(
    private val sessionManager: SessionManager
) {

    fun getToken(): String? {
        return runBlocking {
            sessionManager.getAuthToken().first()
        }
    }
}
