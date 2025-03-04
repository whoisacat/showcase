package com.whoisacat.showcase.view.renderer

import com.whoisacat.showcase.infrastructure.dto.ResumeDto

interface ResumeRenderer {
    fun resumePage(resume: ResumeDto, fields: List<String>? = null): String
}
