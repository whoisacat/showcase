package com.whoisacat.showcase.service

import com.whoisacat.showcase.contract.back.dto.ResumeRedactingDto
import com.whoisacat.showcase.contract.back.dto.ResumeReadingDto
import com.whoisacat.showcase.contract.back.dto.ResumeListDto
import com.whoisacat.showcase.infrastructure.ResumeReadingClient
import com.whoisacat.showcase.infrastructure.ResumeRedactingClient
import org.springframework.stereotype.Service

@Service
class ResumeService(
    private val readingClient: ResumeReadingClient,
    private val redactingClient: ResumeRedactingClient
) {

    fun getReadDto(id: String): ResumeReadingDto {
        return readingClient.getCurrent(id, emptyList())
    }

    fun getCreateDto(id: String): ResumeRedactingDto {
        return redactingClient.getCurrentForEdit(id)
    }

    fun update(dto: ResumeRedactingDto): ResumeRedactingDto {
        return redactingClient.update(dto)
    }

    fun findAll(): List<ResumeListDto> {
        return readingClient.findAll()
    }
}
