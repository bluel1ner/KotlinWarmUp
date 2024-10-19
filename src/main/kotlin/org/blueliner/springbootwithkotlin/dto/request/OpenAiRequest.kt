package org.blueliner.springbootwithkotlin.dto.request

data class OpenAiRequest (
    val model: String,
    val max_tokens: Int,
    val temperature: Double,
    val messages: List<MessageDto>,
    val response_format: Map<String, String>
)