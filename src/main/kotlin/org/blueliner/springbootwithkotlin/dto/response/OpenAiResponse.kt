package org.blueliner.springbootwithkotlin.dto.response

data class OpenAiResponse(
    val choices: List<ChoiceDto>
) {
    data class ChoiceDto(
        val message: ContentDto
    )

    data class ContentDto (
        val content: String
    )
}