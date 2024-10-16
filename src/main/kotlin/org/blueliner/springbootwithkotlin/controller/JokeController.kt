package org.blueliner.springbootwithkotlin.controller

import org.blueliner.springbootwithkotlin.service.JokeService
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class JokeController (private val jokeService: JokeService) {

    @GetMapping("/joke")
    fun getFunnyJokesForClient() :ChatResponse {
        return jokeService.generateAndGetJoke();
    }
}