package com.whoisacat.showcase.application.service

import com.whoisacat.showcase.domain.entity.Summary

interface SummaryService {
    fun get(): Summary
}
