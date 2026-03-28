package com.example.myfirstapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myfirstapplication.model.Chat
import com.example.myfirstapplication.ui.screens.ChatScreen
import com.example.myfirstapplication.ui.screens.HomeScreen

@Composable
fun AppNavGraph(chats: MutableList<Chat>) {
    val navController = rememberNavController()

    val nextChatId = remember {
        mutableIntStateOf((chats.maxOfOrNull { it.chatId } ?: 0) + 1)
    }

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                chats = chats,
                onChatClick = { chatId ->
                    navController.navigate("chat/$chatId")
                },
                onNewChatSelected = { modelName ->
                    val newChat = Chat(
                        chatId = nextChatId.intValue,
                        name = modelName
                    )
                    chats.add(0, newChat)
                    navController.navigate("chat/${newChat.chatId}")
                    nextChatId.intValue += 1
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

            val chat = chats.find { it.chatId == chatId}

            ChatScreen(
                title = chat?.name ?: "Chat",
                chatId = chatId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}