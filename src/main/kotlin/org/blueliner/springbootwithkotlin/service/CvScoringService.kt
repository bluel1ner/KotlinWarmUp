package org.blueliner.springbootwithkotlin.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.blueliner.springbootwithkotlin.dto.response.CvScoringBySectionResponse
import org.blueliner.springbootwithkotlin.dto.response.CvScoringResponse
import org.blueliner.springbootwithkotlin.utils.PdfUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class CvScoringService {
    val objectMapper = ObjectMapper()

    @Autowired
    lateinit var aiClient: AiClientService

    fun getCommonCvScoring(file: MultipartFile): CvScoringResponse {
        val pdfPayload = PdfUtils.parsePdfToString(file)
        val message: String =
            "Hey! I have a cv and you should score it by range from 0 to 100 and respond in json" +
                " with 'name', 'title' and 'score' and give 'explanation'. Cv attached below " + pdfPayload;
        var cvScoringJson = aiClient.getCvScoring(message.plus(pdfPayload))
        return objectMapper.readValue(cvScoringJson, CvScoringResponse::class.java)
    }

    fun getCvScoringBySection(file: MultipartFile): CvScoringBySectionResponse {
        val pdfPayload = PdfUtils.parsePdfToString(file)
        val message: String =
            "Hey! I have a cv and you should score it by range from 0 to 100 by section and respond in json" +
                " with 'name', 'title', 'totalScore' that include total score by each section and list 'sections' which contains 'score' and 'explanation'. Cv attached below " + pdfPayload;
        var cvScoringJson = aiClient.getCvScoring(message.plus(pdfPayload))
        return objectMapper.readValue(cvScoringJson, CvScoringBySectionResponse::class.java)
    }

}