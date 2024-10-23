package org.blueliner.springbootwithkotlin.service

import org.blueliner.springbootwithkotlin.config.AiModelProperties
import org.blueliner.springbootwithkotlin.dto.request.MessageDto
import org.blueliner.springbootwithkotlin.dto.request.OpenAiRequest
import org.blueliner.springbootwithkotlin.dto.response.OpenAiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class AiClientService {
    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var openAiProperties: AiModelProperties;
    val BEARER_TOKEN = "Bearer ";

    fun getCvScoring(cvPayload: String): String {
        val requestEntity = HttpEntity(getBody(cvPayload), getHeaders())
        return restTemplate.postForEntity(
            openAiProperties.url,
            requestEntity,
            OpenAiResponse::class.java
        ).body?.choices?.firstOrNull()?.message?.content ?: "No response from assistant"
    }


    private fun getHeaders(): HttpHeaders {
        return HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set(HttpHeaders.AUTHORIZATION, BEARER_TOKEN.plus(openAiProperties.key))
        }
    }

    private fun getBody(message: String) = OpenAiRequest(
        openAiProperties.model,
        openAiProperties.config.maxTokens,
        openAiProperties.config.temperature,
        listOf(MessageDto("user", message)),
        mapOf("type" to "json_object")
    )
}