package com.whoisacat.showcase.infrastructure.dto

import java.time.LocalDate

class PersonDto(
    val lastName: String,
    val firstName: String,
    val birthDate: LocalDate,
    val city: String,
    val citizenship: String
)
