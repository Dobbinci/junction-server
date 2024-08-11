package com.be.havesomefun.application.service

import com.be.havesomefun.application.dto.IOLDto
import com.be.havesomefun.domain.entity.InformationOfLocation
import com.be.havesomefun.domain.entity.Member
import com.be.havesomefun.domain.entity.MemberIOL
import com.be.havesomefun.domain.repository.MemberIOLRepository
import com.be.havesomefun.presentation.response.BookMarkResponse
import com.be.havesomefun.presentation.response.IOLResponse
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter

@Service
class BookMarkService(
    private val memberIOLRepository: MemberIOLRepository
) {
    fun saveBookMark(member: Member, iol: InformationOfLocation) {
        val memberIOL = MemberIOL.of(member, iol)
        memberIOLRepository.save(memberIOL)
    }

    fun getBookMarkList(member: Member) : List<BookMarkResponse> {
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val bookMarkList : List<BookMarkResponse> = member.memberIOLs
            ?.map {IOLDto.of(it.iol ?: InformationOfLocation(), it.createdTime?.format(dateFormatter).toString())}
            ?.map {BookMarkResponse.of(it)} ?: listOf()
        return bookMarkList
    }
}