package com.example.branchmessaging.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.branchmessaging.ui.login.LoginScreen
import com.example.branchmessaging.ui.threads.ThreadsScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable("login") {
            LoginScreen(navController)
        }

        composable("threads") {
            ThreadsScreen(navController)
        }

        composable("chat/{threadId}") {
            // ConversationScreen will be added later
        }
    }
}
