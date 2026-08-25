package com.flip.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter
import org.springframework.web.client.RestClient
import tools.jackson.databind.json.JsonMapper

@Configuration
class RestClientConfig {

    @Bean
    fun restClientBuilder(objectMapper: JsonMapper): RestClient.Builder {
        return RestClient.builder()
            .messageConverters { converters ->
                converters.add(0, JacksonJsonHttpMessageConverter(objectMapper))
            }
    }
}
