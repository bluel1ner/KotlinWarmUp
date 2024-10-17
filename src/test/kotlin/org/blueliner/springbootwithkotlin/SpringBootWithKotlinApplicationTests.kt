package org.blueliner.springbootwithkotlin

import org.blueliner.springbootwithkotlin.dto.JokeDto
import org.blueliner.springbootwithkotlin.service.JokeService
import org.junit.jupiter.api.Test
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.ChatClient.PromptUserSpec
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@SpringBootTest
class SpringBootWithKotlinApplicationTests {

    @Autowired
    private lateinit var jokeService: JokeService

    @Autowired
    private lateinit var chat: OpenAiChatModel

    @Test
    fun testIsJokeReturnsTrueForEachJokeInList() {
        val jokes = jokeService.getJokesUsingCustomOutput()
        jokes.forEach { joke ->
            run {
                val isJoke = isJoke(joke)
                println("Joke: ${joke.jokeDescription} ? $isJoke")
                assertTrue { isJoke }
            }
        }
    }

    @Test
    fun testIsJokeReturnsFalseForRandomString() {
        assertFalse { isJoke(JokeDto("Hello my name is Jack")) }
    }

    @Test
    fun testIsJokesReturnsTrueForListOfJokes() {
        val jokes = jokeService.getJokesUsingCustomOutput()
        assertTrue { isJokes(jokes) }
    }


    private fun isJokes(jokes: List<JokeDto>): Boolean {
        return ChatClient.create(chat).prompt()
            .user { u: PromptUserSpec ->
                u.text("Are these answers jokes {jokes} ?").param("jokes", jokes)
            }
            .call()
            .entity(Boolean::class.java)
    }

    private fun isJoke(jokeDto: JokeDto): Boolean {
        return ChatClient.create(chat).prompt()
            .user { u: PromptUserSpec ->
                u.text("Is this answer joke {joke} ?").param("joke", jokeDto.jokeDescription)
            }
            .call()
            .entity(Boolean::class.java)
    }

}
