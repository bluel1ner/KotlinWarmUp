package org.blueliner.springbootwithkotlin.service

import com.fasterxml.jackson.databind.ObjectMapper
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import org.blueliner.springbootwithkotlin.dto.response.CvScoringBySectionResponse
import org.blueliner.springbootwithkotlin.dto.response.CvScoringResponse
import org.blueliner.springbootwithkotlin.service.temporal.workflow.GenerateAiCvResponseBySectionWorkflow
import org.blueliner.springbootwithkotlin.service.temporal.workflow.GenerateAiCvResponseWorkflow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import java.util.*

@Service
class CvScoringService {
    val objectMapper = ObjectMapper()

    @Autowired
    lateinit var aiClient: AiClientService

    @Autowired
    lateinit var client: WorkflowClient


    fun getCommonCvScoring(file: MultipartFile): CvScoringResponse {
        val worker = client.newWorkflowStub(
            GenerateAiCvResponseWorkflow::class.java,
            WorkflowOptions.newBuilder()
                .setWorkflowId(UUID.randomUUID().toString().plus("-").plus(LocalDateTime.now().toString()))
                .setTaskQueue("GenenerateAiCvResponseQueue")
                .setMemo(mapOf("fileSize" to file.size))
                .build()
        )
        //A single argument is limited to a maximum size of 2 MB.
        // And the total size of a gRPC message,
        // which includes all the arguments, is limited to a maximum of 4 MB.
        return worker.generateAiCvResponseWorkflow(file.bytes)
    }

    fun getCvScoringBySection(file: MultipartFile): CvScoringBySectionResponse {
        val worker = client.newWorkflowStub(
            GenerateAiCvResponseBySectionWorkflow::class.java,
            WorkflowOptions.newBuilder()
                .setWorkflowId(UUID.randomUUID().toString().plus("-").plus(LocalDateTime.now().toString()))
                .setTaskQueue("GenenerateAiCvResponseBySectionQueue")
                .setMemo(mapOf("fileSize" to file.size))
                .build()
        )

        return worker.generateAiCvResponseWorkflow(file.bytes)
    }

}