package com.whoisacat.showcase.application.service

import com.whoisacat.showcase.domain.entity.Resume

interface ResumeService {
    fun get(): Resume
    fun get(id: String): Resume
}
