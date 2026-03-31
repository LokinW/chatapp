package com.example.myfirstapplication.model

enum class LlmModel(
    val title: String,
    val subtitle: String
) {
    LLAMA_3_1("Llama 3.1", "Balanced general assistant"),
    MISTRAL("Mistral", "Fast and lightweight"),
    GEMMA_2("Gemma 2", "Strong instruction following"),
    PHI_3("Phi-3", "Compact and efficient")
}