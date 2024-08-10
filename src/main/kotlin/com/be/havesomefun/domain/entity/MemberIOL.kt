package com.be.havesomefun.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class MemberIOL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "member_id")
    var member: Member? = null

    @ManyToOne
    @JoinColumn(name = "iol_id")
    var iol: InformationOfLocation? = null

    companion object {
        fun of (member: Member, iol: InformationOfLocation) : MemberIOL {
            val memberIOL = MemberIOL()
            memberIOL.member = member
            memberIOL.iol = iol
            return memberIOL
        }
    }
}