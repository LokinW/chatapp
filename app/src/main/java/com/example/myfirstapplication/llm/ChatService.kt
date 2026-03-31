package com.example.myfirstapplication.llm

class ChatService(private val model: ChatModel) {

    fun sendMessage(userInput: String, onResult: (String) -> Unit) {
        model.generate(userInput) { reply ->
            onResult(reply)
        }
    }
}