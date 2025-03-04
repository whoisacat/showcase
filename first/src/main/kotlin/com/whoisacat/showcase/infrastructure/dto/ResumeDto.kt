package com.whoisacat.showcase.infrastructure.dto

data class ResumeDto(
    val id: String? = null,
    val person: PersonDto,
    val contacts: Set<ContactDto>,
    val label: String,
    val cooperationForms: Set<String>,
    val aboutMe: String,
    val experience: Set<ExperienceDto>,
    val edu: Set<EducationDto>,
    val skills: Set<String>
)
