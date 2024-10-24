package org.blueliner.springbootwithkotlin.service.temporal.activity.impl

import com.fasterxml.jackson.databind.ObjectMapper
import io.temporal.spring.boot.ActivityImpl
import org.blueliner.springbootwithkotlin.dto.response.CvScoringBySectionResponse
import org.blueliner.springbootwithkotlin.dto.response.CvScoringResponse
import org.blueliner.springbootwithkotlin.service.AiClientService
import org.blueliner.springbootwithkotlin.service.temporal.activity.CvScoringActivity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@ActivityImpl(taskQueues = ["GenenerateAiCvResponseQueue", "GenenerateAiCvResponseBySectionQueue"])
class CvScoringActivityImpl : CvScoringActivity {
    @Autowired
    lateinit var aiClient: AiClientService
    val objectMapper = ObjectMapper()

    val messageForBasicCvScoring: String =
        """I have a cv and you should score it by range from 0 to 100 and respond in json with 'name',
                | 'title' and 'score' and give 'explanation'. Cv attached below""".trimMargin();

    val messageForCvScoringByEachSection: String =
        """I have a cv and you should score it by range from 0 to 100 by section and respond in json with 'name', 
                | 'title', 'totalScore' that include total score by each section and list 'sections' 
                | which contains 'score' and 'explanation'. Cv attached below """.trimMargin();

    override fun getCvScoreWithAI(pdfPayload: String): CvScoringResponse {
        return objectMapper.readValue(
            aiClient.getCvScoring(messageForBasicCvScoring.plus(pdfPayload)),
            CvScoringResponse::class.java
        )

    }

    override fun getCvScoreBySectionWithAI(pdfPayload: String): CvScoringBySectionResponse {
        return objectMapper.readValue(
            aiClient.getCvScoring(messageForCvScoringByEachSection.plus(pdfPayload)),
            CvScoringBySectionResponse::class.java
        )
    }
}