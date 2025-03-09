package com.whoisacat.showcase.infrastructure.controller

import com.whoisacat.showcase.application.service.ResumeService
import com.whoisacat.showcase.infrastructure.dto.ResumeCDto
import com.whoisacat.showcase.infrastructure.dto.ResumeDto
import org.springframework.web.bind.annotation.*

@RestController
class ResumeRestController(
    private val resumeService: ResumeService
) {

    @GetMapping("/resume/{id}")
    fun getCurrent(@PathVariable id: String, @RequestParam fields: List<String>?): ResumeDto {
        return resumeService.get(id)
    }

    @PutMapping("/resume/{id}")
    fun getCurrentEditor(@RequestBody dto: ResumeCDto): ResumeDto {
        return resumeService.update(dto)
    }
}
