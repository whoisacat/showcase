package com.whoisacat.showcase.config

import feign.RequestInterceptor
import feign.RequestTemplate
import feign.codec.Encoder
import feign.form.spring.SpringFormEncoder
import feign.okhttp.OkHttpClient
import mu.KotlinLogging
import org.springframework.beans.factory.ObjectFactory
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken

@Configuration
class FeignConfig(private val authorizedClientService: OAuth2AuthorizedClientService) {

    private val logger = KotlinLogging.logger {}

    @Bean
    fun client(): OkHttpClient {
        return OkHttpClient()
    }

    @Bean
    fun feignFormEncoder(messageConverters: ObjectFactory<HttpMessageConverters?>?): Encoder {
        return SpringFormEncoder(SpringEncoder(messageConverters))
    }

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            val token: String? = getAccessToken(authorizedClientService)
            logger.info("Access token: {}", token)
            requestTemplate.header("Authorization", "Bearer $token")
        }
    }
    fun getAccessToken(authorizedClientService: OAuth2AuthorizedClientService): String? {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication is OAuth2AuthenticationToken) {
            val client: OAuth2AuthorizedClient = authorizedClientService.loadAuthorizedClient(
                authentication.authorizedClientRegistrationId,
                authentication.name
            ) ?: return null
            return client.accessToken.tokenValue
        }
        return null
    }
}
