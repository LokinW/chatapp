package com.example.myfirstapplication.llm

class ChatService(private val model: DummyLLM = DummyLLM()) {

    val messages = mutableListOf<String>()

    fun sendMessage(userInput: String, onResult: (String) -> Unit) {
        messages.add("You: $userInput")
        model.generate(userInput) { reply ->
            messages.add("LLM: $reply")
            onResult(reply)
        }
    }
}