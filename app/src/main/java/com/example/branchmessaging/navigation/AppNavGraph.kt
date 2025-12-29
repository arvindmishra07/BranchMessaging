package com.example.branchmessaging.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.branchmessaging.ui.chat.ChatScreen
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

        composable(
            route = "chat/{threadId}",
            arguments = listOf(
                navArgument("threadId") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val threadId = backStackEntry.arguments?.getInt("threadId") ?: return@composable

            ChatScreen(threadId = threadId)
        }

    }
}
