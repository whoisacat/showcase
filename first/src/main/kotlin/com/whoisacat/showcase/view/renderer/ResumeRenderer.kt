package com.whoisacat.showcase.view.renderer

import com.whoisacat.showcase.domain.entity.Resume

interface ResumeRenderer {
    fun resumePage(resume: Resume, fields: List<String>? = null): String
}
