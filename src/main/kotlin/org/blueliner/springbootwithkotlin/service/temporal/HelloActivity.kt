package org.blueliner.springbootwithkotlin.service.temporal

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface HelloActivity {
    fun greeting(name: String): String
}