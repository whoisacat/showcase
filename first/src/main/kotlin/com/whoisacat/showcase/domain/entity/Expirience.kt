package com.whoisacat.showcase.domain.entity

data class Expirience(
    val position: String,
    val datePeriod: DatePeriod,
    val companyTitle: String,
    val companyCity: String,
    val description: String,
    val achievements: Set<String>,
    val technologies: Set<String>
)
