package org.blueliner.springbootwithkotlin.service.temporal.workflow

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import org.blueliner.springbootwithkotlin.dto.response.CvScoringBySectionResponse

@WorkflowInterface
interface GenerateAiCvResponseBySectionWorkflow {
    @WorkflowMethod
    fun generateAiCvResponseWorkflow(file: ByteArray): CvScoringBySectionResponse;
}