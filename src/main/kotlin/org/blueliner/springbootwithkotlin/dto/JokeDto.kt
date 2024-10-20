package org.blueliner.springbootwithkotlin.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class JokeDto @JsonCreator constructor(
    @JsonProperty("jokeDescription") val jokeDescription: String
)