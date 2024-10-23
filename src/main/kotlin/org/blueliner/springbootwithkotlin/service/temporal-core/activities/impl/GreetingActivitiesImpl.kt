package org.blueliner.springbootwithkotlin.service.`temporal-core`.activities.impl

import org.blueliner.springbootwithkotlin.service.`temporal-core`.activities.GreetingActivities

class GreetingActivitiesImpl : GreetingActivities {
    override fun composeGreeting(greetings: String, name: String) = "$greetings $name"
}