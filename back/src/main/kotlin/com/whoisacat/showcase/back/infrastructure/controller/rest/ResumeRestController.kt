package com.whoisacat.showcase.back.infrastructure.controller.rest

import com.whoisacat.showcase.back.application.service.ResumeService
import com.whoisacat.showcase.contract.back.controller.rest.ResumeRestContract
import com.whoisacat.showcase.contract.back.dto.ResumeCDto
import com.whoisacat.showcase.contract.back.dto.ResumeDto
import com.whoisacat.showcase.contract.back.dto.ResumeListDto
import mu.KotlinLogging
import org.springframework.web.bind.annotation.RestController

@RestController
class ResumeRestController(
    private val resumeService: ResumeService
) : ResumeRestContract {

    private val logger = KotlinLogging.logger {}

    init {
        logger.trace { "${this.javaClass.simpleName} is started" }
    }

    override fun getCurrent(id: String, fields: List<String>?): ResumeDto {
        return resumeService.getReadDto(id)
    }

    override fun getCurrentForEdit(id: String): ResumeCDto {
        return resumeService.getCreateDto(id)
    }

    override fun update(dto: ResumeCDto): ResumeCDto {
        return resumeService.update(dto)
    }

    override fun findAll(): List<ResumeListDto> {
        return resumeService.findAll()
    }
}
