package com.whoisacat.showcase.domain.infrastructure

import com.whoisacat.showcase.domain.entity.Resume

interface ResumeRepository {
    fun save(resume: Resume)
    fun get(): Resume
    fun get(id: String): Resume

}
