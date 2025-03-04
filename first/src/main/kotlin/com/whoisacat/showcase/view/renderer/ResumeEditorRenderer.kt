package com.whoisacat.showcase.view.renderer

import com.whoisacat.showcase.infrastructure.dto.ResumeDto

interface ResumeEditorRenderer {
    fun resumeEditorPage(resume: ResumeDto): String
}
