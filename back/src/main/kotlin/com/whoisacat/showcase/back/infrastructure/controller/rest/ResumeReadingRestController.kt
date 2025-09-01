package com.whoisacat.showcase.back.infrastructure.controller.rest

import com.whoisacat.showcase.back.application.service.ResumeService
import com.whoisacat.showcase.contract.back.controller.rest.ResumeReadingContract
import com.whoisacat.showcase.contract.back.dto.ResumeReadingDto
import com.whoisacat.showcase.contract.back.dto.ResumeListDto
import mu.KotlinLogging
import org.springframework.web.bind.annotation.RestController

@RestController
class ResumeReadingRestController(
    private val resumeService: ResumeService
) : ResumeReadingContract {

    private val logger = KotlinLogging.logger {}

    init {
        logger.trace { "${this.javaClass.simpleName} is started" }
    }

    override fun getCurrent(id: String, fields: List<String>?): ResumeReadingDto {
        return resumeService.getReadDto(id)
    }

    override fun findAll(): List<ResumeListDto> {
        return resumeService.findAll()
    }
}
