package com.whoisacat.showcase.back.domain.entity

data class Experience(
    val position: String,
    val datePeriod: DatePeriod,
    val companyTitle: String,
    val companyCity: String,
    val description: String,
    val achievements: Set<String>,
    val technologies: Set<String>
)
