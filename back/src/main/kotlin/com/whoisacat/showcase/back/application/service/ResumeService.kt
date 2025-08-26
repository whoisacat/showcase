package com.whoisacat.showcase.back.application.service

import com.whoisacat.showcase.contract.back.dto.ResumeCDto
import com.whoisacat.showcase.contract.back.dto.ResumeDto

interface ResumeService {
    fun getReadDto(): ResumeDto
    fun getReadDto(id: String): ResumeDto
    fun getCreateDto(id: String): ResumeCDto
    fun update(dto: ResumeCDto): ResumeCDto
}
