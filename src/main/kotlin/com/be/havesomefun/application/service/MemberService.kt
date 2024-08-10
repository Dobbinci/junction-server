package com.be.havesomefun.application.service

import com.be.havesomefun.application.dto.MemberDto
import com.be.havesomefun.domain.entity.Member
import com.be.havesomefun.domain.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun saveMember(memberDto: MemberDto) {
        memberRepository.save(Member.of(memberDto))
    }

    fun getMemberById(id: Long) : Member{
        return memberRepository.findById(id).get()
    }

}