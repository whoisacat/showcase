package com.whoisacat.showcase.service

import com.whoisacat.showcase.contract.back.dto.ResumeCDto
import com.whoisacat.showcase.contract.back.dto.ResumeDto
import com.whoisacat.showcase.infrastructure.ResumeClient
import org.springframework.stereotype.Service

@Service
class ResumeService(val client: ResumeClient) {

    fun getReadDto(id: String): ResumeDto {
        return client.getCurrent(id, emptyList())
    }

    fun getCreateDto(id: String): ResumeCDto {
        return client.getCurrentForEdit(id)
    }

    fun update(dto: ResumeCDto): ResumeCDto {
        return client.update(dto)
    }
}
