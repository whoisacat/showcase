package com.whoisacat.showcase.application.service

import com.whoisacat.showcase.domain.entity.Resume
import com.whoisacat.showcase.domain.infrastructure.ResumeRepository
import org.springframework.stereotype.Service

@Service
class SimpleResumeService(private val repository: ResumeRepository) : ResumeService {

    override fun get(): Resume {
        return repository.get()
    }

    override fun get(id: String): Resume {
        return repository.get(id)
    }
}