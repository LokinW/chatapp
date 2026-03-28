package com.example.myfirstapplication.llm

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ChatViewModel(private val service: ChatService = ChatService()) : ViewModel() {

    val messages = mutableStateListOf<String>()

    fun sendMessage(userInput: String) {
        service.sendMessage(userInput) { reply ->
            // Nachricht aus Service in ViewModel-State übertragen
            messages.add("LLM: $reply")
        }
        messages.add("You: $userInput")
    }
}