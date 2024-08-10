package com.be.havesomefun.domain.repository.querydsl.impl

import com.be.havesomefun.domain.entity.InformationOfLocation
import com.be.havesomefun.domain.entity.QInformationOfLocation
import com.be.havesomefun.domain.repository.querydsl.IOLRepositoryCustom
import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory

class IOLRepositoryCustomImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : IOLRepositoryCustom {
    override fun findByFilters(
        time: String,
        minDecibel: Double,
        maxDecibel: Double,
        minLux: Double,
        maxLux: Double,
        minPopulation: Double,
        maxPopulation: Double,
        geohash7: String
    ): MutableList<InformationOfLocation> {

        val informationOfLocation = QInformationOfLocation.informationOfLocation
        val builder = BooleanBuilder()

        if (geohash7.isNotEmpty()) {
            builder.and(informationOfLocation.geohash7.eq(geohash7))
        }

        if (time.isNotEmpty()) {
            builder.and(informationOfLocation.time.contains(time))
        }

        if (minPopulation != 0.0) {
            builder.and(informationOfLocation.population.goe(minPopulation))
        }

        if (maxPopulation != 0.0) {
            builder.and(informationOfLocation.population.loe(maxPopulation))
        }

        if (minLux != 0.0) {
            builder.and(informationOfLocation.avgLightLux.goe(minLux))
        }

        if (maxLux != 0.0) {
            builder.and(informationOfLocation.avgLightLux.loe(maxLux))
        }

        if (minDecibel != 0.0) {
            builder.and(informationOfLocation.avgDecibel.goe(minDecibel))
        }

        if (maxDecibel != 0.0) {
            builder.and(informationOfLocation.avgDecibel.loe(maxDecibel))
        }

        return jpaQueryFactory.selectFrom(informationOfLocation)
            .where(builder)
            .fetch()

    }
}