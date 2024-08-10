package com.be.havesomefun.application.service

import com.be.havesomefun.domain.entity.InformationOfLocation
import com.be.havesomefun.domain.entity.Member
import com.be.havesomefun.domain.entity.MemberIOL
import com.be.havesomefun.domain.repository.MemberIOLRepository
import org.springframework.stereotype.Service

@Service
class BookMarkService(
    private val memberIOLRepository: MemberIOLRepository
) {
    fun saveBookMark(member: Member, iol: InformationOfLocation) {
        val memberIOL = MemberIOL.of(member, iol)
        memberIOLRepository.save(memberIOL)
    }
}