package com.whoisacat.showcase.application.service

import com.whoisacat.showcase.application.mapper.ResumeMapper
import com.whoisacat.showcase.domain.infrastructure.ResumeRepository
import com.whoisacat.showcase.infrastructure.dto.ResumeCDto
import com.whoisacat.showcase.infrastructure.dto.ResumeDto
import org.springframework.stereotype.Service

@Service
class SimpleResumeService(
    private val repository: ResumeRepository,
    private val mapper: ResumeMapper
) : ResumeService {

    override fun get(): ResumeDto {
        return mapper.map(repository.get())
    }

    override fun get(id: String): ResumeDto {
        return mapper.map(repository.get(id))
    }

    override fun update(dto: ResumeCDto): ResumeDto {
        if (dto.id == null) {
            TODO("")
        } else {
            var resume = repository.get(dto.id)
            resume = mapper.map(dto, resume)
            return mapper.map(repository.save(resume))
        }
    }
}