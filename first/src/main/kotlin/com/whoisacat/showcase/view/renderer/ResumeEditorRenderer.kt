package com.whoisacat.showcase.view.renderer

import com.whoisacat.showcase.infrastructure.dto.ResumeCDto

interface ResumeEditorRenderer {
    fun resumeEditorPage(resume: ResumeCDto): String
}
