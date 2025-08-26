package com.whoisacat.showcase.back

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableWebSecurity
class ShowcaseBackApplication

fun main(args: Array<String>) {
	runApplication<ShowcaseBackApplication>(*args)
}
