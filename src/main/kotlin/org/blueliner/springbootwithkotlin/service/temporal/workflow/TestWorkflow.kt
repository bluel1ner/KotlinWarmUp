package org.blueliner.springbootwithkotlin.service.temporal.workflow

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface NotifyUserAccounts {

    @WorkflowMethod
    fun testWorkFlowMethod(str : String) : Int
}