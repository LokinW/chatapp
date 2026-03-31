package com.example.myfirstapplication.llm

class DummyLLM(private val name: String = "Dummy") : ChatModel {
    override fun generate(prompt: String, onResult: (String) -> Unit) {
        onResult("Dummy-Antwort für: \"$prompt\"")
    }
}