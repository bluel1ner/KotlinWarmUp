package org.blueliner.springbootwithkotlin.service.temporal.workflow

import io.temporal.api.common.v1.WorkflowExecution
import io.temporal.api.enums.v1.WorkflowIdReusePolicy
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.client.WorkflowOptions
import io.temporal.common.RetryOptions
import io.temporal.common.SearchAttributeKey
import io.temporal.common.SearchAttributes
import io.temporal.internal.logging.LoggerTag.WORKFLOW_ID
import io.temporal.serviceclient.WorkflowServiceStubs
import java.net.URL
import java.time.Duration
import java.util.*


var TASK_QUEUE = "FileProcessing"
val SERVICE = WorkflowServiceStubs.newLocalServiceStubs()

fun main(args: Array<String>) {
//    synchronousWorkflowExecution()
//    asynchronousWorkflowExecution()
    test()
}

fun test() {
    val client = WorkflowClient.newInstance(SERVICE)
    val workflowOptions =
        WorkflowOptions.newBuilder()
            .setTaskQueue(TASK_QUEUE)
            .setWorkflowId(WORKFLOW_ID)
            .setWorkflowExecutionTimeout(Duration.ofSeconds(5))
            //TODO difference between first and second wto
            .setWorkflowRunTimeout(Duration.ofSeconds(5))
            .setWorkflowTaskTimeout(Duration.ofSeconds(5))
            .setWorkflowIdReusePolicy(WorkflowIdReusePolicy.WORKFLOW_ID_REUSE_POLICY_ALLOW_DUPLICATE)
            .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(2).build())
            .setCronSchedule("* * * * *")
//            https://docs.temporal.io/workflows#memo
            .setMemo(
                mapOf(
                    "desc" to "This is memo"
                )
            )
            .setTypedSearchAttributes(
                SearchAttributes.newBuilder().set(SearchAttributeKey.forText("TypedSearchAttributes"), "test attribute") .build()
            )
            .build()
    val workflow = client.newUntypedWorkflowStub("DynamicWF", workflowOptions)
    workflow.start()
}

fun asynchronousWorkflowExecution() {
    // gRPC stubs wrapper that talks to the local docker instance of temporal service.

    // client that can be used to start and signal workflows
    val client = WorkflowClient.newInstance(SERVICE)
    val workflow: FileProcessingWorkflow =
        client.newWorkflowStub(
            FileProcessingWorkflow::class.java,
            WorkflowOptions.newBuilder().setWorkflowId(UUID.randomUUID().toString()).setTaskQueue(TASK_QUEUE).build()
        )

    println("Executing FileProcessingWorkflow")

    val source = URL("http://www.google.com/")
    val destination = URL("http://dummy")


    // This is going to block until the workflow completes.
    // This is rarely used in production. Use the commented code below for async start version.
    //    workflow.processFile(source, destination)

    // use WorkflowClient.execute to return future that contains Workflow result or failure, or
    // use WorkflowClient.start to return WorkflowId and RunId of the started Workflow).
    val workflowStartResult: WorkflowExecution = WorkflowClient.start(workflow::processFile, source, destination)
    println("WorkflowStartResult: $workflowStartResult")
    val workflowExecuteResult = WorkflowClient.execute(workflow::processFile, source, destination)
    println("WorkflowExecuteResult: $workflowExecuteResult.")

    println("Executed")
}

fun synchronousWorkflowExecution() {
    val clientOptions = WorkflowClientOptions.newBuilder().setNamespace("default").build()
    val client = WorkflowClient.newInstance(SERVICE, clientOptions)

    val workflow: NotifyUserAccounts = client.newWorkflowStub(
        NotifyUserAccounts::class.java,
        WorkflowOptions.newBuilder()
            .setWorkflowId("notifyAccounts")
            .setTaskQueue("YourTaskQueue")
            .build()
    )

    val testWorkFlowMethod = workflow.testWorkFlowMethod("notifyAccounts2")
    println(testWorkFlowMethod)
}