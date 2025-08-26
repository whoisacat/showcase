package com.whoisacat.showcase.controller

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController {

    @GetMapping("/")
    @ResponseBody
    fun index(@AuthenticationPrincipal user: OidcUser?): String {
        return createHTML().html {
            body {
                h1 { +"Главная страница" }

                if (user == null) {
                    p {
                        a("/oauth2/authorization/keycloak") { +"Войти через Keycloak" }
                    }
                } else {
                    p { +"Привет, ${user.fullName}!" }
                    h2 { +"Твои authority:" }
                    ul {
                        user.authorities.forEach {
                            li { +it.authority }
                        }
                    }
                    p {
                        a(href = "/resume-editor/67bb7af64f12d6111dbc858f") {+"editor"}
                    }
                    p {
                        a("/logout") { +"Выйти" }
                    }
                }
            }
        }
    }
}
