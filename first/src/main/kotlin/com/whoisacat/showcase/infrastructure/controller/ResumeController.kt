package com.whoisacat.showcase.infrastructure.controller

import com.whoisacat.showcase.application.service.ResumeService
import com.whoisacat.showcase.infrastructure.dto.ResumeCDto
import com.whoisacat.showcase.infrastructure.dto.ResumeDto
import com.whoisacat.showcase.view.renderer.ResumeEditorRenderer
import com.whoisacat.showcase.view.renderer.ResumeRenderer
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class ResumeController(
    private val resumeService: ResumeService,
    private val resumeRenderer: ResumeRenderer,
    private val editRenderer: ResumeEditorRenderer
) {

    private val logger = KotlinLogging.logger {}

    @GetMapping("/resume-page/{id}", produces = [TEXT_HTML_UTF8])
    fun getCurrent(@PathVariable id: String, @RequestParam fields: List<String>?): String {
        val resume: ResumeDto = resumeService.getReadDto(id)
        logger.trace { "resume id ${resume.id}" }
        return resumeRenderer.resumePage(resume, fields)
    }

    @GetMapping("/resume-editor/{id}", produces = [TEXT_HTML_UTF8])
    fun getCurrentEditor(@PathVariable id: String, @RequestParam fields: List<String>?): String {
        val resume: ResumeCDto = resumeService.getCreateDto(id)
        logger.trace { "resume id ${resume.id}" }
        return editRenderer.resumeEditorPage(resume)
    }

    @PutMapping("/resume-editor/{id}", produces = [TEXT_HTML_UTF8])
    fun getCurrentEditor(@RequestBody dto: ResumeCDto): String {
        val resume: ResumeCDto = resumeService.update(dto)
        logger.trace { "resume id ${resume.id} is updated" }
        return editRenderer.resumeEditorPage(resume)
    }

    companion object {
        const val TEXT_HTML_UTF8 = "${MediaType.TEXT_HTML_VALUE};charset=UTF-8"
    }
}
