package com.whoisacat.showcase.config

import com.nimbusds.jwt.JWTParser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(private val clientRegistrationRepository: ClientRegistrationRepository) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                it.requestMatchers("/", "/logout", "/error").permitAll()
                it.requestMatchers(
                    HttpMethod.GET,
                    "img.png",
                    "logo.png",
                    "logo.svg",
                    "favicon.ico",
                    "/resume-page/*"
                ).permitAll()
                it.anyRequest().authenticated()
            }
            .oauth2Login { login ->
                login.userInfoEndpoint { userInfo ->
                    userInfo.oidcUserService(customOidcUserService())
                }
            }
            .logout { logout ->
                logout.logoutSuccessHandler(oidcLogoutSuccessHandler())
            }

        return http.build()
    }

    private fun customOidcUserService(): OidcUserService {
        val delegate = OidcUserService()

        return object : OidcUserService() {
            override fun loadUser(userRequest: OidcUserRequest): OidcUser {
                val oidcUser = delegate.loadUser(userRequest)

                val mappedAuthorities = mutableSetOf<SimpleGrantedAuthority>()
                mappedAuthorities.addAll(oidcUser.authorities.map { SimpleGrantedAuthority(it.authority) })

                val tokenValue = userRequest.accessToken.tokenValue

                val jwt = JWTParser.parse(tokenValue)
                val claims = jwt.jwtClaimsSet.claims

                val realmAccess = claims["realm_access"] as? Map<*, *>
                val roles = realmAccess?.get("roles") as? Collection<*>

                roles?.forEach { role ->
                    mappedAuthorities.add(SimpleGrantedAuthority("ROLE_$role"))
                }

                return DefaultOidcUser(mappedAuthorities, oidcUser.idToken, oidcUser.userInfo)
            }
        }
    }

    private fun oidcLogoutSuccessHandler(): OidcClientInitiatedLogoutSuccessHandler {
        val handler = OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository)
        handler.setPostLogoutRedirectUri("http://show-case:8090/")
        return handler
    }
}
