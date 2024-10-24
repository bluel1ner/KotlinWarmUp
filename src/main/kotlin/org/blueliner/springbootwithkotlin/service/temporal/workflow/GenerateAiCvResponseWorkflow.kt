package org.blueliner.springbootwithkotlin.service.temporal.workflow

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import org.blueliner.springbootwithkotlin.dto.response.CvScoringResponse

@WorkflowInterface
interface GenerateAiCvResponseWorkflow {
    @WorkflowMethod
    fun generateAiCvResponseWorkflow(file: ByteArray): CvScoringResponse;
}