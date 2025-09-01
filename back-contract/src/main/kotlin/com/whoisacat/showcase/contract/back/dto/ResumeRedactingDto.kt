package com.whoisacat.showcase.contract.back.dto

data class ResumeRedactingDto(
    val id: String? = null,
    val person: PersonDto,
    val contacts: Set<ContactDto>,
    val label: String,
    val cooperationForms: Set<String>,
    val aboutMe: String,
    val experience: Set<ExperienceCDto>,
    val edu: Set<EducationDto>,
    val skills: Set<String>
)
