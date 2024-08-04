package com.whoisacat.showcase.infrastructure.controller

import com.whoisacat.showcase.application.service.SummaryService
import com.whoisacat.showcase.domain.entity.Summary
import com.whoisacat.showcase.view.renderer.SummaryRenderer
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
class ShowcaseController(
    private val summaryService: SummaryService,
    private val summaryRenderer: SummaryRenderer
) {

    @GetMapping("/whoisacat", produces = [TEXT_HTML_UTF8])
    fun getCurrent(): String {
        val summary: Summary = summaryService.get()
        return summaryRenderer.summaryPage(summary)
    }

    companion object {
        const val TEXT_HTML_UTF8 = "${MediaType.TEXT_HTML_VALUE};charset=UTF-8"
    }
}