package com.example.myfirstapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myfirstapplication.model.Chat
import com.example.myfirstapplication.navigation.AppNavGraph

@Composable
fun ChatApp(
    modifier: Modifier = Modifier,
) {
    val chats = listOf(
        Chat(1, "Chat 1"),
        Chat(2, "Chat 2"),
        Chat(3, "Chat 3"),
        Chat(4, "Chat 4"),
        Chat(5, "Chat 5"),
        Chat(6, "Chat 6"),
        Chat(7, "Chat 7"),
        Chat(8, "Chat 8"),
        Chat(9, "Chat 9"),
        Chat(10, "Chat 10"),
        Chat(11, "Chat 11"),
        Chat(12, "Chat 12"),
    )

    AppNavGraph(chats = chats)
}