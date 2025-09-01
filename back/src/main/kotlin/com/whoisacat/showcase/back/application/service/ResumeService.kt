package com.whoisacat.showcase.back.application.service

import com.whoisacat.showcase.contract.back.dto.ResumeRedactingDto
import com.whoisacat.showcase.contract.back.dto.ResumeReadingDto
import com.whoisacat.showcase.contract.back.dto.ResumeListDto

interface ResumeService {
    fun getReadDto(): ResumeReadingDto
    fun getReadDto(id: String): ResumeReadingDto
    fun getCreateDto(id: String): ResumeRedactingDto
    fun update(dto: ResumeRedactingDto): ResumeRedactingDto
    fun findAll(): List<ResumeListDto>
}
