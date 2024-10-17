package org.blueliner.springbootwithkotlin.controller

import org.blueliner.springbootwithkotlin.dto.JokeDto
import org.blueliner.springbootwithkotlin.service.JokeService
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Optional


@RestController
@RequestMapping("/jokes")
class JokeController(private val jokeService: JokeService) {

    @GetMapping("/ai-format")
    fun getJokeUsingBasicAiModelOutput(): ResponseEntity<ChatResponse> {
        return ResponseEntity.of(Optional.of(jokeService.getJokeUsingBasicAiModelOutput()));
    }

    @GetMapping("/custom-format")
    fun getJokeUsingCustomOutput(): ResponseEntity<List<JokeDto>> {
        return ResponseEntity.of(Optional.of(jokeService.getJokesUsingCustomOutput()));
    }

    @GetMapping("/test")
    fun test(): ResponseEntity<ChatResponse> {
        return ResponseEntity.of(Optional.of(jokeService.generateJokeResponseWithDifferentRoles()));
    }

}