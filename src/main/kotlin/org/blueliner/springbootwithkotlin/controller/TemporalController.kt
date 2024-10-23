package org.blueliner.springbootwithkotlin.controller

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import org.blueliner.springbootwithkotlin.service.`temporal-core`.workflow.GreetingWorkflow
import org.blueliner.springbootwithkotlin.service.temporal.HelloWorkflow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/temporal")
class TemporalController {
    @Autowired
    lateinit var client : WorkflowClient;


    @GetMapping("/greeting")
    fun greeting(): String {
        val workflow: HelloWorkflow =
            client.newWorkflowStub(
                HelloWorkflow::class.java,
                WorkflowOptions.newBuilder()
                    .setTaskQueue("HelloSampleTaskQueue")
                    .setWorkflowId("My first spring boot temporal workflow id")
                    .build()
            )
        return workflow.helloWorkflow("Ilya")
    }
}