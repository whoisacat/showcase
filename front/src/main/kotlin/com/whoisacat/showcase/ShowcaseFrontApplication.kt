package com.whoisacat.showcase

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ShowcaseFrontApplication

fun main(args: Array<String>) {
	runApplication<ShowcaseFrontApplication>(*args)
}
