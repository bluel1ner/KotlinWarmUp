package org.blueliner.springbootwithkotlin.service.temporal

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface HelloWorkflow {
    @WorkflowMethod
    fun helloWorkflow(name : String) : String
}