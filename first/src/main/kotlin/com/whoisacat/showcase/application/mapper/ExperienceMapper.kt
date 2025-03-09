package com.whoisacat.showcase.application.mapper

import com.whoisacat.showcase.domain.entity.Experience
import com.whoisacat.showcase.infrastructure.dto.ExperienceDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ExperienceMapper {

    @Mapping(target = "datePeriod", expression = "java(source.getDatePeriod().toString())")
    fun map(source: Experience) : ExperienceDto
}
