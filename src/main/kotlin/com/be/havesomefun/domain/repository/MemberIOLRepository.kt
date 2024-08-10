package com.be.havesomefun.domain.repository

import com.be.havesomefun.domain.entity.MemberIOL
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberIOLRepository : JpaRepository<MemberIOL, Long> {

}