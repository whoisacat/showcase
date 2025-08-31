package com.whoisacat.showcase.back.domain.infrastructure

import com.whoisacat.showcase.back.domain.entity.Resume

interface ResumeRepository {
    fun save(resume: Resume): Resume
    fun get(): Resume
    fun get(id: String): Resume
    fun findAll(): List<Resume>
}
