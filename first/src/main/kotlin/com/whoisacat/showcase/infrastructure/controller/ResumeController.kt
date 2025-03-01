package com.whoisacat.showcase.infrastructure.controller

import com.whoisacat.showcase.application.service.ResumeService
import com.whoisacat.showcase.domain.entity.Resume
import com.whoisacat.showcase.view.renderer.ResumeRenderer
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class ResumeController(
    private val resumeService: ResumeService,
    private val resumeRenderer: ResumeRenderer
) {

    private val logger = KotlinLogging.logger {}

    @GetMapping("/resume/whoisacat", produces = [TEXT_HTML_UTF8])
    fun getCurrent(): String {
        val resume: Resume = resumeService.get("67bb7af64f12d6111dbc858f")
        logger.trace { "resume id ${resume.id}" }
        return resumeRenderer.resumePage(resume)
    }

    @GetMapping("/resume/{id}", produces = [TEXT_HTML_UTF8])
    fun getCurrent(@PathVariable id: String, @RequestParam fields: List<String>?): String {
        val resume: Resume = resumeService.get(id)
        logger.trace { "resume id ${resume.id}" }
        return resumeRenderer.resumePage(resume, fields)
    }

    companion object {
        const val TEXT_HTML_UTF8 = "${MediaType.TEXT_HTML_VALUE};charset=UTF-8"
    }
}