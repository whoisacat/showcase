package com.whoisacat.showcase.contract.back.dto

data class ResumeListDto(
    val id: String,
    val person: PersonDto,
    val label: String,
    val cooperationForms: Set<String>
)
