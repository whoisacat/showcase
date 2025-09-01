package com.whoisacat.showcase.view.renderer

import com.whoisacat.showcase.contract.back.dto.ResumeRedactingDto
import org.springframework.security.web.csrf.CsrfToken

interface ResumeEditorRenderer {
    fun resumeEditorPage(resume: ResumeRedactingDto, csrf: CsrfToken): String
}
