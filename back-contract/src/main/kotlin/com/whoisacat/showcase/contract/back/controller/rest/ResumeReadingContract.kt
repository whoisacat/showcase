package com.whoisacat.showcase.contract.back.controller.rest

import com.whoisacat.showcase.contract.back.dto.ResumeReadingDto
import com.whoisacat.showcase.contract.back.dto.ResumeListDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
interface ResumeReadingContract {

    companion object {
        const val PATH = "/resume/read"
    }

    @GetMapping("$PATH/{id}")
    fun getCurrent(@PathVariable id: String, @RequestParam(required = false) fields: List<String>?): ResumeReadingDto

    @GetMapping(PATH)
    fun findAll(): List<ResumeListDto>
}
