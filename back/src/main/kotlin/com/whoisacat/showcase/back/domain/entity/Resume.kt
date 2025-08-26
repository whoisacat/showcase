package com.whoisacat.showcase.back.domain.entity

data class Resume(
    var id: String? = null,
    var person: Person,
    var contacts: Set<Contact>,
    var label: String,
    var cooperationForms: Set<String>,
    var aboutMe: String,
    var experience: Set<Experience>,
    var edu: Set<Education>,
    var skills: Set<String>
)
