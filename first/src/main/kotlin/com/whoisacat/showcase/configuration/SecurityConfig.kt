//package com.whoisacat.cashflow.configuration
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.Customizer
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
//import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer
//import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.web.SecurityFilterChain
//
//
//@Configuration
//@EnableWebSecurity
//class SecurityConfig {
//    @Bean
//    @Throws(Exception::class)
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .httpBasic { obj: HttpBasicConfigurer<HttpSecurity> -> obj.disable() }
//            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
////            .authorizeHttpRequests(
////                Customizer { authorize ->
////                    authorize
////                        .requestMatchers("/swagger-ui.html/", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
////                        .anyRequest().permitAll()
////                }
////            )
//            .sessionManagement {
//                configurer: SessionManagementConfigurer<HttpSecurity?> -> configurer
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            }
//            .oauth2ResourceServer { configurer: OAuth2ResourceServerConfigurer<HttpSecurity?> -> configurer
//                .jwt(Customizer.withDefaults())
//            }
//        return http.build()
//    }
//}