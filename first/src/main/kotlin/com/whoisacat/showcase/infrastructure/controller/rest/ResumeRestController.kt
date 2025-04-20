package com.whoisacat.showcase.infrastructure.controller.rest

import com.whoisacat.showcase.application.service.ResumeService
import com.whoisacat.showcase.infrastructure.dto.ResumeCDto
import com.whoisacat.showcase.infrastructure.dto.ResumeDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ResumeRestController(
    private val resumeService: ResumeService
) {

    @GetMapping("/resume/{id}")
    fun getCurrent(@PathVariable id: String, @RequestParam fields: List<String>?): ResumeDto {
        return resumeService.getReadDto(id)
    }

    @PutMapping("/resume/{id}")
    fun getCurrentEditor(@RequestBody dto: ResumeCDto): ResumeCDto {
        return resumeService.update(dto)
    }
}
