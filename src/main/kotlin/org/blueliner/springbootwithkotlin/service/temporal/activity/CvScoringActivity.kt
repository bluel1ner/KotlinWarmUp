package org.blueliner.springbootwithkotlin.service.temporal.activity

import io.temporal.activity.ActivityInterface
import org.blueliner.springbootwithkotlin.dto.response.CvScoringBySectionResponse
import org.blueliner.springbootwithkotlin.dto.response.CvScoringResponse

@ActivityInterface
interface CvScoringActivity {
    fun getCvScoreWithAI(pdfPayload: String) : CvScoringResponse

    fun getCvScoreBySectionWithAI(pdfPayload: String) : CvScoringBySectionResponse
}