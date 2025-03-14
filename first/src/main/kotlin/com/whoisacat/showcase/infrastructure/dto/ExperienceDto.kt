package com.whoisacat.showcase.infrastructure.dto

data class ExperienceDto(
    val position: String,
    val datePeriod: String,
    val companyTitle: String,
    val companyCity: String,
    val description: String,
    val achievements: Set<String>,
    val technologies: Set<String>
)
