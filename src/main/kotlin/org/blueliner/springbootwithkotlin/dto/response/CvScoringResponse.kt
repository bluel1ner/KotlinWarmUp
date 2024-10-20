package org.blueliner.springbootwithkotlin.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class CvScoringResponse(
    @JsonProperty("name") val name: String?,
    @JsonProperty("title") val title: String?,
    @JsonProperty("score") val score: Int?,
    @JsonProperty("explanation") val explanation: String?
)