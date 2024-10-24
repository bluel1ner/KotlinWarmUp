package org.blueliner.springbootwithkotlin.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(AiModelProperties::class)
class AppConfiguration {
}