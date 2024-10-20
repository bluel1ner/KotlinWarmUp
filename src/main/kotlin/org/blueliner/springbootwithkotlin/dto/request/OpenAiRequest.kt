package org.blueliner.springbootwithkotlin.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class OpenAiRequest (
    val model: String,
    @JsonProperty("max_tokens")
    val maxTokens: Int,
    val temperature: Double,
    val messages: List<MessageDto>,
    @JsonProperty("response_format")
    val responseFormat: Map<String, String>
)