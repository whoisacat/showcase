package com.whoisacat.showcase.controller

import com.whoisacat.showcase.contract.back.dto.ResumeRedactingDto
import com.whoisacat.showcase.contract.back.dto.ResumeReadingDto
import com.whoisacat.showcase.service.ResumeService
import com.whoisacat.showcase.view.renderer.ResumeEditorRenderer
import com.whoisacat.showcase.view.renderer.ResumeRenderer
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.bind.annotation.*

@RestController
class ResumeController(
    private val resumeService: ResumeService,
    private val resumeRenderer: ResumeRenderer,
    private val editRenderer: ResumeEditorRenderer
) {

    private val logger = KotlinLogging.logger {}

    companion object {
        const val TEXT_HTML_UTF8 = "${MediaType.TEXT_HTML_VALUE};charset=UTF-8"
    }

    @GetMapping("/resume-page/{id}", produces = [TEXT_HTML_UTF8])
    fun getById(@PathVariable id: String, @RequestParam fields: List<String>?): String {
        val resume: ResumeReadingDto = resumeService.getReadDto(id)
        logger.trace { "resume id ${resume.id}" }
        return resumeRenderer.resumePage(resume, fields)
    }

    @GetMapping("/resume-editor/{id}", produces = [TEXT_HTML_UTF8])
    fun getEditorById(@PathVariable id: String, request: HttpServletRequest): String {
        val resume: ResumeRedactingDto = resumeService.getCreateDto(id)
        logger.trace { "resume id ${resume.id}" }
        val csrf = request.getAttribute("_csrf") as CsrfToken
        return editRenderer.resumeEditorPage(resume, csrf)
    }

    @PutMapping("/resume-editor/{id}", produces = [TEXT_HTML_UTF8])
    fun getUpdatedEditor(@RequestBody dto: ResumeRedactingDto, request: HttpServletRequest): String {
        val resume: ResumeRedactingDto = resumeService.update(dto)
        logger.trace { "resume id ${resume.id} is updated" }
        val csrf = request.getAttribute("_csrf") as CsrfToken
        return editRenderer.resumeEditorPage(resume, csrf)
    }
}
