package org.blueliner.springbootwithkotlin.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class CvScoringBySectionResponse(
    @JsonProperty("name") val name: String?,
    @JsonProperty("title") val title: String?,
    @JsonProperty("sections") val sections: List<Section>?,
    @JsonProperty("totalScore") val totalScore: Int?
)

data class Section(
    @JsonProperty("section") val section: String?,
    @JsonProperty("score") val score: Int?,
    @JsonProperty("explanation") val explanation: String?
)