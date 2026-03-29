package com.example.myfirstapplication.llm

interface ChatModel {
    fun generate(prompt: String, onResult: (String) -> Unit)
}

class DummyLLM : ChatModel {
    override fun generate(prompt: String, onResult: (String) -> Unit) {
        onResult("Dummy-Antwort für: \"$prompt\"")
    }
}