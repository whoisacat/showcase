package com.whoisacat.showcase.application.mapper

import com.whoisacat.showcase.domain.entity.Person
import com.whoisacat.showcase.infrastructure.dto.PersonDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PersonMapper {
    fun map(dto: PersonDto): Person
    fun map(person: Person): PersonDto
}
