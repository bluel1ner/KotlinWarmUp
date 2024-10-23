package org.blueliner.springbootwithkotlin.service.temporal.impl

import io.temporal.spring.boot.ActivityImpl
import org.blueliner.springbootwithkotlin.service.temporal.HelloActivity
import org.springframework.stereotype.Component

@Component
@ActivityImpl(taskQueues = ["HelloSampleTaskQueue"])
class HelloActivityImpl : HelloActivity {
    override fun greeting(name: String) = "Hello $name"
}