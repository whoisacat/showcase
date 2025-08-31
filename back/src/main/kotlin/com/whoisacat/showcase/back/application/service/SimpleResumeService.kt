package com.whoisacat.showcase.back.application.service

import com.whoisacat.showcase.back.application.mapper.PersonMapper
import com.whoisacat.showcase.back.application.mapper.ResumeMapper
import com.whoisacat.showcase.contract.back.dto.ResumeCDto
import com.whoisacat.showcase.contract.back.dto.ResumeDto
import com.whoisacat.showcase.back.domain.infrastructure.ResumeRepository
import com.whoisacat.showcase.contract.back.dto.ResumeListDto
import java.lang.RuntimeException
import org.springframework.stereotype.Service

@Service
class SimpleResumeService(
    private val repository: ResumeRepository,
    private val mapper: ResumeMapper,
    private val personMapper: PersonMapper
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
            throw RuntimeException("Can not update resume without id")
        } else {
            val resume = repository.get(dto.id!!)
            mapper.update(dto, resume)
            return mapper.mapToCreate(repository.save(resume))
        }
    }

    override fun findAll(): List<ResumeListDto> {
        return repository.findAll().stream()
            .map {
                ResumeListDto(
                    id = it.id!!,
                    person = personMapper.map(it.person),
                    label = it.label,
                    cooperationForms = it.cooperationForms
                )
            }.toList()
    }
}