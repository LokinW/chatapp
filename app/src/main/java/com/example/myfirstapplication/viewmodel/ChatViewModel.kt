package com.example.myfirstapplication.llm

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.myfirstapplication.model.ChatMessage

class ChatViewModel(private val service: ChatService = ChatService()) : ViewModel() {

    val messages = mutableStateListOf<ChatMessage>()

    fun sendMessage(userInput: String) {

        messages.add(ChatMessage(text = userInput, isUser = true))

        service.sendMessage(userInput) { reply ->
            // Nachricht aus Service in ViewModel-State übertragen
            messages.add(ChatMessage(text = reply, isUser = false))
        }
    }
}