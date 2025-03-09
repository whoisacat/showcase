package com.whoisacat.showcase.application.service

import com.whoisacat.showcase.infrastructure.dto.ResumeCDto
import com.whoisacat.showcase.infrastructure.dto.ResumeDto

interface ResumeService {
    fun get(): ResumeDto
    fun get(id: String): ResumeDto
    fun update(dto: ResumeCDto): ResumeDto
}
