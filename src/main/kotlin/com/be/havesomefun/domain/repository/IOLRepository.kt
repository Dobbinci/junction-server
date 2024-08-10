package com.be.havesomefun.domain.repository

import com.be.havesomefun.domain.entity.InformationOfLocation
import com.be.havesomefun.domain.repository.querydsl.IOLRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IOLRepository : JpaRepository<InformationOfLocation, Long>, IOLRepositoryCustom {

    override fun findByFilters(
        time: String,
        minDecibel: Double,
        maxDecibel: Double,
        minLux: Double,
        maxLux: Double,
        minPopulation: Double,
        maxPopulation: Double,
        geohash7: String
    ): MutableList<InformationOfLocation>
}