package com.flip.common.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig {

    private val log = LoggerFactory.getLogger(RestClientConfig::class.java)

    @Bean
    fun restClientBuilder(): RestClient.Builder {
        return RestClient.builder()
            .requestInterceptor(ClientHttpRequestInterceptor { request, body, execution ->
                log.info(
                    "[RestClient] {} {} contentType={} bodyBytes={} bodyPreview={}",
                    request.method,
                    request.uri,
                    request.headers.contentType,
                    body.size,
                    String(body).take(500)
                )
                execution.execute(request, body)
            })
    }
}
