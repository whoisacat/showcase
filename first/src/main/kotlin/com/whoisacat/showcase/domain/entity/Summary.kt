package com.whoisacat.showcase.domain.entity

data class Summary(
    val person: Person,
    val contacts: Set<Contact>,
    val label: String,
    val cooperationForms: Set<String>,
    val aboutMe: String,
    val expirience: Set<Expirience>,
    val edu: Set<Education>,
    val skills: Set<String>
)
