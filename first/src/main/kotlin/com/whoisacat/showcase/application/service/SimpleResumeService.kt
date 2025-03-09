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

    override fun getReadDto(): ResumeDto {
        return mapper.mapToRead(repository.get())
    }

    override fun getReadDto(id: String): ResumeDto {
        return mapper.mapToRead(repository.get(id))
    }

    override fun getCreateDto(id: String): ResumeCDto {
        return mapper.mapToCreate(repository.get(id))
    }

    override fun update(dto: ResumeCDto): ResumeCDto {
        if (dto.id == null) {
            TODO("")
        } else {
            var resume = repository.get(dto.id)
            resume = mapper.mapToRead(dto, resume)
            return mapper.mapToCreate(repository.save(resume))
        }
    }
}