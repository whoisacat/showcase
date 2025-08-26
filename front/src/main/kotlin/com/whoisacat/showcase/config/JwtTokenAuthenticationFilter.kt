package com.whoisacat.showcase.config

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
class JwtTokenAuthenticationFilter() : OncePerRequestFilter() {
    private val log = KotlinLogging.logger {}

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (SecurityContextHolder.getContext().authentication.javaClass != null) {
            log.info("authenticated {}", SecurityContextHolder.getContext().authentication.isAuthenticated)
        } else {
            log.info("method {} uri {}", request.method, request.requestURI)
            log.info("authenticated {}", SecurityContextHolder.getContext().authentication.isAuthenticated)
        }
        filterChain.doFilter(request, response)
    }
}