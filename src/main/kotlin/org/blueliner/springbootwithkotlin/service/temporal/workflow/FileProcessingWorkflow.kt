package org.blueliner.springbootwithkotlin.service.temporal.workflow

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import java.net.URL

@WorkflowInterface
interface FileProcessingWorkflow {
    @WorkflowMethod
    fun processFile(source: URL?, destination: URL?)
}