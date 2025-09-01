package com.whoisacat.showcase.back.configuration

import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler

@Configuration
@EnableWebSecurity
class SecurityConfig {
    private val logger = KotlinLogging.logger {}

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .httpBasic { obj: HttpBasicConfigurer<HttpSecurity> -> obj.disable() }
            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }

            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/resume/read", "/resume/read/*")
                    .permitAll()
                    .requestMatchers("/resume/redact/*", "/resume/redact/**")
                    .hasRole("redactor")
                    .anyRequest().authenticated()
            }
            .sessionManagement {
                configurer: SessionManagementConfigurer<HttpSecurity?> -> configurer
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .exceptionHandling {
                this.accessDeniedHandler()
            }
            .oauth2ResourceServer { configurer: OAuth2ResourceServerConfigurer<HttpSecurity?> -> configurer
                .jwt {
                    jwtAuthenticationConverter()
                }
            }
        return http.build()
    }

    @Bean
    fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val converter = JwtAuthenticationConverter()

        converter.setJwtGrantedAuthoritiesConverter { jwt: Jwt ->
            val authorities = mutableListOf<GrantedAuthority>()

            val realmRoles = ((jwt.claims["realm_access"] as? Map<*, *>)?.get("roles") as? List<*>)
                ?.map { "ROLE_$it" } ?: emptyList()
            authorities.addAll(realmRoles.map { SimpleGrantedAuthority(it) })

            val clientId = "your-client-id"
            val clientRoles = ((jwt.claims["resource_access"] as? Map<*, *>)?.get(clientId) as? Map<*, *>)
                ?.get("roles") as? List<*>
            if (clientRoles != null) {
                authorities.addAll(clientRoles.map { SimpleGrantedAuthority("ROLE_$it") })
            }
            authorities
        }
        return converter
    }

    @Bean
    fun accessDeniedHandler(): AccessDeniedHandler = AccessDeniedHandler { request, response, accessDeniedException ->
        logger.trace { accessDeniedException}
        logger.warn("Access denied for user ${request.userPrincipal?.name} at ${request.requestURI}")
        response.sendError(HttpServletResponse.SC_FORBIDDEN)
    }
}