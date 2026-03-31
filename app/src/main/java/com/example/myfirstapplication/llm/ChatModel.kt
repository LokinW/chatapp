package com.example.myfirstapplication.llm

interface ChatModel {
    fun generate(prompt: String, onResult: (String) -> Unit)
}