package com.whoisacat.showcase.application.mapper

import com.whoisacat.showcase.domain.entity.Resume
import com.whoisacat.showcase.infrastructure.dto.ResumeCDto
import com.whoisacat.showcase.infrastructure.dto.ResumeDto
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring", uses = [
    PersonMapper::class,
    ExperienceMapper::class
])
interface ResumeMapper {
    fun mapToRead(dto: ResumeCDto, @MappingTarget resume: Resume): Resume
    fun mapToRead(resume: Resume): ResumeDto
    fun mapToCreate(resume: Resume): ResumeCDto
}
