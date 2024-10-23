package org.blueliner.springbootwithkotlin.service.`temporal-core`

import io.temporal.api.enums.v1.WorkflowIdReusePolicy.WORKFLOW_ID_REUSE_POLICY_ALLOW_DUPLICATE
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.Worker
import io.temporal.worker.WorkerFactory
import org.blueliner.springbootwithkotlin.service.`temporal-core`.activities.impl.GreetingActivitiesImpl
import org.blueliner.springbootwithkotlin.service.`temporal-core`.workflow.GreetingWorkflow
import org.blueliner.springbootwithkotlin.service.`temporal-core`.workflow.impl.GreetingWorkflowImpl

val service = WorkflowServiceStubs.newLocalServiceStubs()
const val TASK_QUEUE = "HelloAsyncActivityCompletionTaskQueue"

fun main(args: Array<String>) {
    init()
}

fun init() {
    val client: WorkflowClient = WorkflowClient.newInstance(service)
    val factory: WorkerFactory = WorkerFactory.newInstance(client)
    val worker: Worker = factory.newWorker(TASK_QUEUE)
    worker.registerWorkflowImplementationTypes(GreetingWorkflowImpl::class.java)
    /*
     * Register our Activity Types with the Worker. Since Activities are stateless and thread-safe,
     * the Activity Type is a shared instance.
     */
    worker.registerActivitiesImplementations(GreetingActivitiesImpl())
    /*
     * Start all the Workers registered for a specific Task Queue. The Workers then start polling
     * for Workflow Tasks and Activity Tasks.
     */
    factory.start()
    // Create the workflow client stub. It is used to start our workflow execution.
    val workflow: GreetingWorkflow =
        client.newWorkflowStub(
            GreetingWorkflow::class.java,
            WorkflowOptions.newBuilder()
                .setWorkflowId("test-id4")
                .setTaskQueue(TASK_QUEUE)
                .setMemo(mapOf("memo" to "memo"))
                .setWorkflowIdReusePolicy(WORKFLOW_ID_REUSE_POLICY_ALLOW_DUPLICATE)
                .build()
        )

    val greeting = WorkflowClient.execute<String, String>(
        { name: String? ->
            workflow.getGreeting(
                name!!
            )
        }, "World"
    )


    println(greeting.get())
}