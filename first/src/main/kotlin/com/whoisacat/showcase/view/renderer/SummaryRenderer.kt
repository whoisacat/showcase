package com.whoisacat.showcase.view.renderer

import com.whoisacat.showcase.domain.entity.Summary

interface SummaryRenderer {
    fun summaryPage(summary: Summary): String
}
