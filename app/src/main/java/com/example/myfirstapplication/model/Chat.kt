package com.example.myfirstapplication.model

data class Chat(
    val chatId: Int,
    val name: String
)

data class ChatMessage(
    val text: String,
    val isUser: Boolean
)

