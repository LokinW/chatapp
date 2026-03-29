package com.example.myfirstapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.myfirstapplication.model.Chat
import com.example.myfirstapplication.navigation.AppNavGraph

@Composable
fun ChatApp(
    modifier: Modifier = Modifier,
) {
    val chats = remember {
        mutableStateListOf<Chat>()
    }
    AppNavGraph(chats = chats)
}