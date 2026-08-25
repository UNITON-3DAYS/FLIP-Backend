package com.flip.common.config

import org.slf4j.LoggerFactory
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.web.client.RestClient
import java.time.Duration

@Configuration
class RestClientConfig {

    private val log = LoggerFactory.getLogger(RestClientConfig::class.java)

    @Bean
    fun restClientBuilder(): RestClient.Builder {
        val requestFactory = ClientHttpRequestFactoryBuilder.detect()
            .build(
                ClientHttpRequestFactorySettings.defaults()
                    .withConnectTimeout(Duration.ofSeconds(10))
                    .withReadTimeout(Duration.ofSeconds(60))
            )

        return RestClient.builder()
            .requestFactory(requestFactory)
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
