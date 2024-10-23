package org.blueliner.springbootwithkotlin.service.`temporal-core`.workflow.impl

import org.blueliner.springbootwithkotlin.service.`temporal-core`.activities.GreetingActivities
import org.blueliner.springbootwithkotlin.service.`temporal-core`.activities.impl.GreetingActivitiesImpl
import org.blueliner.springbootwithkotlin.service.`temporal-core`.workflow.GreetingWorkflow

class GreetingWorkflowImpl : GreetingWorkflow {

    val activities: GreetingActivities = GreetingActivitiesImpl()

    override fun getGreeting(name: String) =
        activities.composeGreeting("Hello", name)

}