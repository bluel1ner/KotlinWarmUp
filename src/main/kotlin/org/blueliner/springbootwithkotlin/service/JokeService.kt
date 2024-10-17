package org.blueliner.springbootwithkotlin.service

import org.blueliner.springbootwithkotlin.dto.JokeDto
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.ChatClient.PromptUserSpec
import org.springframework.ai.chat.messages.Message
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.chat.prompt.SystemPromptTemplate
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service


@Service
class JokeService(
    private val chat: OpenAiChatModel,
) {
    @Value("classpath:/prompts/system-message.st")
    private val systemResource: Resource? = null


    fun getJokeUsingBasicAiModelOutput(): ChatResponse {
        return chatClient()
            .prompt().user("Generate a random joke")
            .call()
            .chatResponse();
    }

    fun getJokesUsingCustomOutput(): List<JokeDto> {
        val responseType = object : ParameterizedTypeReference<List<JokeDto>>() {}
        return chatClient().prompt()
            .user { u: PromptUserSpec ->
                u.text("Generate five random jokes")
            }
            .call()
            .entity(responseType);
    }

    fun generateJokeResponseWithDifferentRoles(): ChatResponse {
        val userText = """
            Tell me the most fascinating joke in the world
            """.trimIndent()

        val userMessage: Message = UserMessage(userText)
        val systemPromptTemplate = SystemPromptTemplate(systemResource)
        val systemMessage = systemPromptTemplate.createMessage(mapOf<String, Any>("name" to "Joke master"))

        val prompt = Prompt(listOf(userMessage, systemMessage))
        return chat.call(prompt);
    }


    private fun chatClient(): ChatClient = ChatClient.create(chat)

}