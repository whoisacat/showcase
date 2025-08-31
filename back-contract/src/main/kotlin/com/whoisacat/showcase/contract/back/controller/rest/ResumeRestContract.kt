package com.whoisacat.showcase.contract.back.controller.rest

import com.whoisacat.showcase.contract.back.dto.ResumeCDto
import com.whoisacat.showcase.contract.back.dto.ResumeDto
import com.whoisacat.showcase.contract.back.dto.ResumeListDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
interface ResumeRestContract {

    companion object {
        const val PATH = "/resume"
    }

    @GetMapping("$PATH/{id}")
    fun getCurrent(@PathVariable id: String, @RequestParam(required = false) fields: List<String>?): ResumeDto

    @GetMapping("$PATH/{id}:for-update/")
    fun getCurrentForEdit(@PathVariable id: String): ResumeCDto

    @PutMapping(PATH)
    fun update(@RequestBody dto: ResumeCDto): ResumeCDto

    @GetMapping(PATH)
    fun findAll(): List<ResumeListDto>
}
