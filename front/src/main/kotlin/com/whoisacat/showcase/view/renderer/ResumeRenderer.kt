package com.whoisacat.showcase.view.renderer

import com.whoisacat.showcase.contract.back.dto.ResumeReadingDto

interface ResumeRenderer {
    fun resumePage(resume: ResumeReadingDto, fields: List<String>? = null): String
}
