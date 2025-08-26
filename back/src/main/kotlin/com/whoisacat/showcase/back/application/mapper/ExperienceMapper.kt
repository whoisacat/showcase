package com.whoisacat.showcase.back.application.mapper

import com.whoisacat.showcase.contract.back.dto.ExperienceDto
import com.whoisacat.showcase.back.domain.entity.Experience
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ExperienceMapper {

    @Mapping(target = "datePeriod", expression = "java(source.getDatePeriod().toString())")
    fun map(source: Experience) : ExperienceDto
}
