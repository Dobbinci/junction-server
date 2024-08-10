package com.be.havesomefun.presentation.controller

import com.be.havesomefun.application.dto.MemberDto
import com.be.havesomefun.application.service.MemberService
import com.be.havesomefun.presentation.request.MemberRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller("/api")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: MemberRequest) {
        memberService.saveMember(MemberDto.of(request))
    }
}