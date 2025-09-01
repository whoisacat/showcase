package com.whoisacat.showcase.infrastructure

import com.whoisacat.showcase.config.FeignConfig
import com.whoisacat.showcase.contract.back.controller.rest.ResumeReadingContract
import org.springframework.cloud.openfeign.FeignClient

@FeignClient(
    name = "resume-reading-fc",
    url = "\${services.resume.url}",
    path = "",
    configuration = [FeignConfig::class]
)
interface ResumeReadingClient : ResumeReadingContract
