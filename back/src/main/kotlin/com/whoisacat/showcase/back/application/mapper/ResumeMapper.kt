package com.whoisacat.showcase.back.application.mapper

import com.whoisacat.showcase.contract.back.dto.ResumeCDto
import com.whoisacat.showcase.contract.back.dto.ResumeDto
import com.whoisacat.showcase.back.domain.entity.Resume
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring", uses = [
    PersonMapper::class,
    ExperienceMapper::class
])
interface ResumeMapper {
    fun mapToRead(dto: ResumeCDto, @MappingTarget resume: Resume)
    fun mapToRead(resume: Resume): ResumeDto
    fun mapToCreate(resume: Resume): ResumeCDto
}
