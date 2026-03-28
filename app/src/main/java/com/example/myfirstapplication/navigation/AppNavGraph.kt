package com.example.myfirstapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myfirstapplication.model.Chat
import com.example.myfirstapplication.ui.screens.ChatScreen
import com.example.myfirstapplication.ui.screens.HomeScreen

@Composable
fun AppNavGraph(chats: List<Chat>) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                chats = chats,
                onChatClick = { chatId ->
                    navController.navigate("chat/$chatId")
                }
            )
        }

        composable(
            route = "chat/{chatId}",
            arguments = listOf(
                navArgument("chatId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val chatId = backStackEntry.arguments?.getInt("chatId") ?: -1
            ChatScreen(
                title = "The chat",
                chatId = chatId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}