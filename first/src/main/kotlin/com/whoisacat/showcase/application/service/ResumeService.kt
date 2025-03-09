package com.whoisacat.showcase.application.service

import com.whoisacat.showcase.infrastructure.dto.ResumeCDto
import com.whoisacat.showcase.infrastructure.dto.ResumeDto

interface ResumeService {
    fun getReadDto(): ResumeDto
    fun getReadDto(id: String): ResumeDto
    fun getCreateDto(id: String): ResumeCDto
    fun update(dto: ResumeCDto): ResumeCDto
}
