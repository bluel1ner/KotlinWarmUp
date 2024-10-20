package org.blueliner.springbootwithkotlin.controller

import org.blueliner.springbootwithkotlin.dto.JokeDto
import org.blueliner.springbootwithkotlin.service.JokeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/jokes")
class JokeController(private val jokeService: JokeService) {

    @GetMapping("/ai-format")
    fun getJokeUsingBasicAiModelOutput(): JokeDto {
        return jokeService.getJokeUsingBasicAiModelOutput()
    }

    @GetMapping("/custom-format")
    fun getJokeUsingCustomOutput(): List<JokeDto> {
        return jokeService.getJokesUsingCustomOutput();
    }

    @GetMapping("/test")
    fun test(): JokeDto {
        return jokeService.generateJokeResponseWithDifferentRoles();
    }

}