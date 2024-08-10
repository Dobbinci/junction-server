package com.be.havesomefun.application.dto

import com.be.havesomefun.presentation.request.MemberRequest

class MemberDto {
    var id: Long? = null
    var name: String? = null
    var email: String? = null
    var password: String? = null

    companion object {
        fun of (memberRequest: MemberRequest) : MemberDto{
            val memberDto = MemberDto()
            memberDto.name = memberRequest.name
            memberDto.email = memberRequest.email
            memberDto.password = memberRequest.password
            return memberDto
        }
    }
}