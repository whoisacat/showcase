package com.whoisacat.showcase.view.renderer

import com.whoisacat.showcase.contract.back.dto.ResumeDto

interface ResumeRenderer {
    fun resumePage(resume: ResumeDto, fields: List<String>? = null): String
}
