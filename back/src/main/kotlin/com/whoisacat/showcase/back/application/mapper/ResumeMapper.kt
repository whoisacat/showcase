package com.whoisacat.showcase.back.application.mapper

import com.whoisacat.showcase.contract.back.dto.ResumeRedactingDto
import com.whoisacat.showcase.contract.back.dto.ResumeReadingDto
import com.whoisacat.showcase.back.domain.entity.Resume
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring", uses = [
    PersonMapper::class,
    ExperienceMapper::class
])
interface ResumeMapper {
    fun update(dto: ResumeRedactingDto, @MappingTarget resume: Resume)
    fun mapToRead(resume: Resume): ResumeReadingDto
    fun mapToCreate(resume: Resume): ResumeRedactingDto
}
