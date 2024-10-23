package org.blueliner.springbootwithkotlin.service.temporal.impl

import io.temporal.activity.LocalActivityOptions
import io.temporal.spring.boot.WorkflowImpl
import io.temporal.workflow.Workflow
import org.blueliner.springbootwithkotlin.service.temporal.HelloActivity
import org.blueliner.springbootwithkotlin.service.temporal.HelloWorkflow
import java.time.Duration

@WorkflowImpl(taskQueues = ["HelloSampleTaskQueue"])
class HelloWorkflowImpl : HelloWorkflow {
    private val activities: HelloActivity = Workflow.newLocalActivityStub(
        HelloActivity::class.java,
        LocalActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build()
    )

    override fun helloWorkflow(name: String): String {
        return activities.greeting(name)
    }
}