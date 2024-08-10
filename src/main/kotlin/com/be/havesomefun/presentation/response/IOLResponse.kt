package com.be.havesomefun.presentation.response

import com.be.havesomefun.application.dto.IOLDto

class IOLResponse {
    var id: Long? = null
    var avgDecibel: Double = 0.0
    var avgLightLux: Double = 0.0
    var population: Double = 0.0
    var geohash7: String? = null

    companion object {
        fun of (iolDto: IOLDto) : IOLResponse {
            val iolResponse = IOLResponse()
            iolResponse.id = iolDto.id
            iolResponse.avgDecibel = iolDto.avgDecibel
            iolResponse.avgLightLux = iolDto.avgLightLux
            iolResponse.population = iolDto.population
            iolResponse.geohash7 = iolDto.geohash7
            return iolResponse
        }
    }
}