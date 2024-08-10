package com.be.havesomefun.presentation.controller

import com.be.havesomefun.application.dto.IOLDto
import com.be.havesomefun.application.dto.MemberDto
import com.be.havesomefun.application.service.BookMarkService
import com.be.havesomefun.application.service.IOLService
import com.be.havesomefun.application.service.MemberService
import com.be.havesomefun.presentation.request.BookMarkRequest
import com.be.havesomefun.presentation.request.MemberRequest
import com.be.havesomefun.presentation.response.IOLResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api")
class MemberController(
    private val memberService: MemberService,
    private val iolService: IOLService,
    private val bookMarkService: BookMarkService
) {

    @PostMapping("/signUp")
    fun signUp(@RequestBody request: MemberRequest) :ResponseEntity<Void> {
        memberService.saveMember(MemberDto.of(request))

        return ResponseEntity.created(URI.create("/")).build()
    }

    @PostMapping("/bookMark")
    fun bookMark(@RequestBody request: BookMarkRequest) : ResponseEntity<Void> {
        val member = memberService.getMemberById(request.memberId!!)
        val iol = iolService.getIOLById(request.iolId!!)
        bookMarkService.saveBookMark(member, iol)

        return ResponseEntity.created(URI.create("/")).build()
    }

    @GetMapping("/bookMark")
    fun getBookMarkList(@RequestParam memberId: Long) : ResponseEntity<List<IOLResponse>> {
        val member = memberService.getMemberById(memberId)
        val bookMarkList = bookMarkService.getBookMarkList(member)

        val response = bookMarkList.map { IOLResponse.of(IOLDto.of(it))}

        return ResponseEntity.ok(response)
    }
}