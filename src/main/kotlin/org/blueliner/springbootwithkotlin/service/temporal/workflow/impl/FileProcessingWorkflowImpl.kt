package org.blueliner.springbootwithkotlin.service.temporal.workflow.impl

import org.blueliner.springbootwithkotlin.service.temporal.workflow.FileProcessingWorkflow
import java.net.URL

class FileProcessingWorkflowImpl : FileProcessingWorkflow {

    override fun processFile(source: URL?, destination: URL?) {
        println(source.toString().plus(destination.toString()))
    }
}