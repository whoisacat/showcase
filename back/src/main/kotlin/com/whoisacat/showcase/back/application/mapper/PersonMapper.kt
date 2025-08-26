package com.whoisacat.showcase.back.application.mapper

import com.whoisacat.showcase.back.domain.entity.Person
import com.whoisacat.showcase.contract.back.dto.PersonDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PersonMapper {
    fun map(dto: PersonDto): Person
    fun map(person: Person): PersonDto
}
