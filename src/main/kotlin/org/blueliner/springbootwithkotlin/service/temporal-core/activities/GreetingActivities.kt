package org.blueliner.springbootwithkotlin.service.`temporal-core`.activities

import io.temporal.activity.ActivityInterface

@ActivityInterface
interface GreetingActivities {
    fun composeGreeting(greetings : String, name : String) : String
}