package com.be.havesomefun.application.dto

import com.be.havesomefun.domain.entity.InformationOfLocation

class IOLDto {
    var id: Long? = null
    var time: String? = null
    var avgDecibel: Double = 0.0
    var avgLightLux: Double = 0.0
    var population: Double = 0.0
    var geohash7: String? = null

    companion object {
        fun of (iol: InformationOfLocation) : IOLDto {
            val iolDto = IOLDto()
            iolDto.id = iol.id
            iolDto.time = iol.time
            iolDto.avgDecibel = iol.avgDecibel
            iolDto.avgLightLux = iol.avgLightLux
            iolDto.population = iol.population
            iolDto.geohash7 = iol.geohash7
            return iolDto
        }
    }
}