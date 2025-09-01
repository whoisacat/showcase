package com.whoisacat.showcase.controller

import com.whoisacat.showcase.contract.back.dto.ResumeListDto
import com.whoisacat.showcase.controller.ResumeController.Companion.TEXT_HTML_UTF8
import com.whoisacat.showcase.service.ResumeService
import com.whoisacat.showcase.view.renderer.MainPageRenderer
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MainController(
    private val renderer: MainPageRenderer,
    private val resumeService: ResumeService
) {

    @GetMapping("/", produces = [TEXT_HTML_UTF8])
    @ResponseBody
    fun index(@AuthenticationPrincipal user: OidcUser?): String {

        val all = resumeService.findAll()
        val publicList: List<ResumeListDto> = all
        val redactingList: List<ResumeListDto> = if (user == null) emptyList() else all
        return renderer.render(
            username = user?.fullName,
            publicResumes = publicList,
            editableResumes = redactingList
        )
    }
}
