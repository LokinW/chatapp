package com.example.myfirstapplication.model

enum class LlmModel(
    val title: String,
    val subtitle: String
) {
    LLAMA_3_1(
        title = "Llama 3.1",
        subtitle = "Balanced general assistant"
    ),
    MISTRAL(
        title = "Mistral",
        subtitle = "Fast and lightweight"
    ),
    GEMMA_2(
        title = "Gemma 2",
        subtitle = "Strong instruction following"
    ),
    PHI_3(
        title = "Phi-3",
        subtitle = "Compact and efficient"
    )
}