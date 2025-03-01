package com.whoisacat.showcase.domain.entity

import java.time.LocalDate

class Person(
    val lastName: String,
    val firstName: String,
    val birthDate: LocalDate,
    val city: String,
    val citizenship: String
)
