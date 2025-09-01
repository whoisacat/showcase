package com.whoisacat.showcase.back.infrastructure.controller.rest

import com.whoisacat.showcase.back.application.service.ResumeService
import com.whoisacat.showcase.contract.back.controller.rest.ResumeRedactingContract
import com.whoisacat.showcase.contract.back.dto.ResumeRedactingDto
import mu.KotlinLogging
import org.springframework.web.bind.annotation.RestController

@RestController
class ResumeRedactingRestController(
    private val resumeService: ResumeService
) : ResumeRedactingContract {

    private val logger = KotlinLogging.logger {}

    init {
        logger.trace { "${this.javaClass.simpleName} is started" }
    }

    override fun getCurrentForEdit(id: String): ResumeRedactingDto {
        return resumeService.getCreateDto(id)
    }

    override fun update(dto: ResumeRedactingDto): ResumeRedactingDto {
        return resumeService.update(dto)
    }
}
