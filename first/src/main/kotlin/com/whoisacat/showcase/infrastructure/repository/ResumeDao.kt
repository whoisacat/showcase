package com.whoisacat.showcase.infrastructure.repository

import com.whoisacat.showcase.domain.entity.Resume
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ResumeDao: MongoRepository<Resume, String> {

}
