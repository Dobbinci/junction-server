package com.be.havesomefun.domain.entity

import com.be.havesomefun.application.dto.MemberDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null
    var password: String? = null
    var email: String? = null

    @OneToMany(mappedBy = "member")
    var memberIOLs: List<MemberIOL>? = null

    companion object {
        fun of (memberDto: MemberDto) : Member {
            val member = Member()
            member.id = memberDto.id
            member.name = memberDto.name
            member.email = memberDto.email
            member.password = memberDto.password
            return member
        }
    }
}