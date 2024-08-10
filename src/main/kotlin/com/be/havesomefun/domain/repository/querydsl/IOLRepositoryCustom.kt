package com.be.havesomefun.domain.repository.querydsl

import com.be.havesomefun.domain.entity.InformationOfLocation


interface IOLRepositoryCustom {

    fun findByFilters(
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
