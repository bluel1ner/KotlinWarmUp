package org.blueliner.springbootwithkotlin.service

import org.blueliner.springbootwithkotlin.dto.request.MessageDto
import org.blueliner.springbootwithkotlin.dto.request.OpenAiRequest
import org.blueliner.springbootwithkotlin.dto.response.OpenAiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class AiClientService(
    @Value("\${openai.url}") val openAiUrl: String,
    @Value("\${openai.key}") val openAiKey: String,
    @Value("\${openai.model}") val model: String,
    @Value("\${openai.config.max-tokens}") val maxTokens: Int,
    @Value("\${openai.config.temperature}") val temperature: Double,
) {
    @Autowired
    lateinit var restTemplate: RestTemplate
    val BEARER_TOKEN = "Bearer ";

    fun getCvScoring(cvPayload: String): String {
        val requestEntity = HttpEntity(getBody(cvPayload), getHeaders())
        return restTemplate.postForEntity(
            openAiUrl,
            requestEntity,
            OpenAiResponse::class.java
        ).body?.choices?.firstOrNull()?.message?.content ?: "No response from assistant"
    }


    private fun getHeaders(): HttpHeaders {
        return HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set(HttpHeaders.AUTHORIZATION, BEARER_TOKEN.plus(openAiKey))
        }
    }

    private fun getBody(message: String) = OpenAiRequest(
        model, maxTokens, temperature, listOf(MessageDto("user", message)), mapOf("type" to "json_object")
    )
}