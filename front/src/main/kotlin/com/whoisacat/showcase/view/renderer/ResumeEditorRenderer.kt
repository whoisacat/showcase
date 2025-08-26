package com.whoisacat.showcase.view.renderer

import com.whoisacat.showcase.contract.back.dto.ResumeCDto
import org.springframework.security.web.csrf.CsrfToken

interface ResumeEditorRenderer {
    fun resumeEditorPage(resume: ResumeCDto, csrf: CsrfToken): String
}
