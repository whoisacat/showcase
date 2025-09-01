package com.whoisacat.showcase.controller

import com.whoisacat.showcase.controller.ResumeController.Companion.TEXT_HTML_UTF8
import com.whoisacat.showcase.view.renderer.ErrorPageRenderer
import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ShowcaseErrorController(
    private val errorPageRenderer: ErrorPageRenderer
) : ErrorController {

    private val logger = KotlinLogging.logger {}

    @GetMapping("/error", produces = [TEXT_HTML_UTF8])
    fun handleError(request: HttpServletRequest): String {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)?.toString() ?: "Неизвестно"
        val msg = request.getAttribute(RequestDispatcher.ERROR_MESSAGE)?.toString()
        val uri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI)?.toString()
        logger.trace { "$uri ${uri?.length}" }
        val ex = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION)?.toString()

        val message = buildString {
            append("Ошибка $status")
            if (!msg.isNullOrBlank()) append(": $msg")
            if (!uri.isNullOrBlank()) append(" (URL: $uri)")
            if (!ex.isNullOrBlank()) append("<br/>$ex")
        }

        return errorPageRenderer.errorPage(message)
    }
}