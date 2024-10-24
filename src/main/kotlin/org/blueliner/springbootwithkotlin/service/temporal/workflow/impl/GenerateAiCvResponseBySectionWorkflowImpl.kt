package org.blueliner.springbootwithkotlin.service.temporal.workflow.impl

import io.temporal.activity.ActivityOptions
import io.temporal.common.RetryOptions
import io.temporal.spring.boot.WorkflowImpl
import io.temporal.workflow.Workflow
import org.blueliner.springbootwithkotlin.dto.response.CvScoringBySectionResponse
import org.blueliner.springbootwithkotlin.service.temporal.activity.CvScoringActivity
import org.blueliner.springbootwithkotlin.service.temporal.activity.ParsePdfActivity
import org.blueliner.springbootwithkotlin.service.temporal.workflow.GenerateAiCvResponseBySectionWorkflow
import java.time.Duration

@WorkflowImpl(taskQueues = ["GenenerateAiCvResponseBySectionQueue"])
class GenerateAiCvResponseBySectionWorkflowImpl : GenerateAiCvResponseBySectionWorkflow {

    private val parsePdfActivity: ParsePdfActivity = Workflow.newActivityStub(
        ParsePdfActivity::class.java,
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(2))
            .build()
    )

    private val cvScoringBySectionActivity: CvScoringActivity = Workflow.newActivityStub(
        CvScoringActivity::class.java,
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(15))
            .setRetryOptions(
                RetryOptions.newBuilder()
                    .setInitialInterval(Duration.ofSeconds(20))
                    .setMaximumInterval(Duration.ofSeconds(30))
                    .setMaximumAttempts(3)
                    .build()
            )
            .build()
    )

    override fun generateAiCvResponseWorkflow(file: ByteArray): CvScoringBySectionResponse {
        val pdfString = parsePdfActivity.parsePdfActivity(file)
        return cvScoringBySectionActivity.getCvScoreBySectionWithAI(pdfString)
    }
}