package org.blueliner.springbootwithkotlin.config

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "openai")
data class AiModelProperties @JsonCreator constructor(
    @JsonProperty("url") val url: String,
    @JsonProperty("key") val key: String,
    @JsonProperty("model") val model: String,
    @JsonProperty("config") val config: OpenAiConfig
) {
    data class OpenAiConfig @JsonCreator constructor(
        @JsonProperty("maxTokens") val maxTokens: Int,
        @JsonProperty("temperature") val temperature: Double
    )
}