package com.whoisacat.showcase.infrastructure

import com.whoisacat.showcase.config.FeignConfig
import com.whoisacat.showcase.contract.back.controller.rest.ResumeRestContract
import org.springframework.cloud.openfeign.FeignClient

@FeignClient(
    name = "resume-fc",
    url = "\${services.resume.url}",
    path = "",
    configuration = [FeignConfig::class]
)
interface ResumeClient : ResumeRestContract
