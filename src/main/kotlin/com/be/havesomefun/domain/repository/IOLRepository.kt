package com.be.havesomefun.domain.repository

import com.be.havesomefun.domain.entity.InformationOfLocation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IOLRepository : JpaRepository<InformationOfLocation, Long> {
    fun findAllByLatitude(latitude: Double) : MutableList<InformationOfLocation>
}