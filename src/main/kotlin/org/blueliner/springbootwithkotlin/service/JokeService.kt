package org.blueliner.springbootwithkotlin.service

import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.stereotype.Service

@Service
class JokeService (private val chat: OpenAiChatModel) {

    fun generateAndGetJoke() : ChatResponse {
        val jokeResponse: ChatResponse = chat.call(
            Prompt(
                "Generate a random joke.",
                OpenAiChatOptions.builder()
                    .withModel("gpt-3.5-turbo")
                    .withTemperature(0.4)
                    .build()
            )
        )
        return  jokeResponse;
    }
}