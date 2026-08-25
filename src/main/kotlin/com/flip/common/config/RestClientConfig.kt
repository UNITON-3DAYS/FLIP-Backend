package com.flip.common.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.JdkClientHttpRequestFactory
import org.springframework.web.client.RestClient
import java.net.http.HttpClient
import java.time.Duration

@Configuration
class RestClientConfig {

    private val log = LoggerFactory.getLogger(RestClientConfig::class.java)

    @Bean
    fun restClientBuilder(): RestClient.Builder {
        // JDK HttpClient 기본값은 HTTP/2 → cleartext(http)에서 h2c 업그레이드를 시도하는데,
        // uvicorn(FastAPI)은 HTTP/1.1만 지원해 "Unsupported upgrade request"로 거부하며
        // 요청 바디가 유실된다 → AI 서버가 body=null로 받아 422. HTTP/1.1로 고정해 막는다.
        val httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build()
        val requestFactory = JdkClientHttpRequestFactory(httpClient).apply {
            setReadTimeout(Duration.ofSeconds(60))
        }

        return RestClient.builder()
            .requestFactory(requestFactory)
            .requestInterceptor(ClientHttpRequestInterceptor { request, body, execution ->
                val bodyString = if (body.isNotEmpty()) String(body, Charsets.UTF_8).take(500) else "<empty>"

                log.info(
                    "[RestClient] {} {} contentType={} bodyBytes={} bodyPreview={}",
                    request.method,
                    request.uri,
                    request.headers.contentType,
                    body.size,
                    bodyString
                )
                execution.execute(request, body)
            })
    }
}
