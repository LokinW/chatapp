package com.example.myfirstapplication.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myfirstapplication.llm.ChatModel
import com.example.myfirstapplication.llm.ChatService
import com.example.myfirstapplication.llm.DummyLLM
import com.example.myfirstapplication.model.LlmModel

class ChatViewModel(selectedModel: LlmModel) : ViewModel() {

    private val service = ChatService(selectedModel.createModel())

    val messages = mutableStateListOf<String>()

    fun sendMessage(userInput: String) {
        messages.add("You: $userInput")

        service.sendMessage(userInput) { reply ->
            messages.add("LLM: $reply")
        }
    }
}

class ChatViewModelFactory(private val selectedModel: LlmModel) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            // Erzeugt das ChatModel über SelectModel und initialisiert den ChatService
            val service = ChatService(SelectModel.createModel(selectedModel))
            return ChatViewModel(service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    private fun createModel(selectedModel: LlmModel): ChatModel {
        return when (selectedModel) {
            LlmModel.LLAMA_3_1 -> DummyLLM("Llama 3.1 Dummy")
            LlmModel.MISTRAL -> DummyLLM("Mistral Dummy")
            LlmModel.GEMMA_2 -> DummyLLM("Gemma 2 Dummy")
            LlmModel.PHI_3 -> DummyLLM("Phi 3 Dummy")
        }
    }
}