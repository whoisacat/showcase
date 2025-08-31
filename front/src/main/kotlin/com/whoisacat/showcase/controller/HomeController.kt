package com.whoisacat.showcase.controller

import com.whoisacat.showcase.contract.back.dto.ResumeListDto
import com.whoisacat.showcase.service.ResumeService
import com.whoisacat.showcase.view.renderer.MainPageRenderer
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController(
    private val renderer: MainPageRenderer,
    private val resumeService: ResumeService
) {

    @GetMapping("/")
    @ResponseBody
    fun index(@AuthenticationPrincipal user: OidcUser?): String {

        val all = if (user == null) emptyList() else resumeService.findAll()
        val publicList: List<ResumeListDto> = all
        val redactingList: List<ResumeListDto> = all
        return renderer.render(
            username = user?.fullName,
            publicResumes = publicList,
            editableResumes = redactingList
        )
    }
}
