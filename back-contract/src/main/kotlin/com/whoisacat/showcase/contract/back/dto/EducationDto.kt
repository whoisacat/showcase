package com.whoisacat.showcase.contract.back.dto

data class EducationDto(
    val type: Type,
    val residenceCity: String,
    val graduationDate: Int,
    val institution: String,
    val faculty: String?,
    val speciality: String,
    val degree: String?
) : Comparable<EducationDto> {
    override fun compareTo(other: EducationDto): Int {
        return this.graduationDate.compareTo(other.graduationDate)
    }

    enum class Type {
        MAIN, TRAINING
    }
}
