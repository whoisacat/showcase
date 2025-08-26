package com.whoisacat.showcase.back.domain.entity

data class Education(
    val type: Type,
    val residenceCity: String,
    val graduationDate: Int,
    val institution: String,
    val faculty: String?,
    val speciality: String,
    val degree: String?
) : Comparable<Education> {
    override fun compareTo(other: Education): Int {
        return this.graduationDate.compareTo(other.graduationDate)
    }

    enum class Type {
        MAIN, TRAINING
    }
}
