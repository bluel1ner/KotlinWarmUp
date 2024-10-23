package org.blueliner.springbootwithkotlin.service.`temporal-core`.namespaces

import com.google.protobuf.util.Durations;
import io.temporal.api.workflowservice.v1.RegisterNamespaceRequest
import io.temporal.serviceclient.WorkflowServiceStubs

val SERVICE = WorkflowServiceStubs.newLocalServiceStubs()

fun main(args: Array<String>) {
    createNamespace()
}

fun createNamespace() {
    val namespaceRequest = RegisterNamespaceRequest.newBuilder()
        .setNamespace("stage-env")
        .setWorkflowExecutionRetentionPeriod(Durations.fromDays(3))
        .build()

    SERVICE.blockingStub().registerNamespace(namespaceRequest)
}