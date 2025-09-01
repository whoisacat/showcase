package com.whoisacat.showcase.contract.back.controller.rest

import com.whoisacat.showcase.contract.back.dto.ResumeRedactingDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
interface ResumeRedactingContract {

    companion object {
        const val PATH = "/resume/redact"
    }

    @GetMapping("$PATH/{id}")
    fun getCurrentForEdit(@PathVariable id: String): ResumeRedactingDto

    @PutMapping(PATH)
    fun update(@RequestBody dto: ResumeRedactingDto): ResumeRedactingDto
}
