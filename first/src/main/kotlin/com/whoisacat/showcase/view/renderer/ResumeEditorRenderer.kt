package com.whoisacat.showcase.view.renderer

import com.whoisacat.showcase.domain.entity.Resume

interface ResumeEditorRenderer {
    fun resumeEditorPage(resume: Resume): String
}
