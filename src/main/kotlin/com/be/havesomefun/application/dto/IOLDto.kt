package com.be.havesomefun.application.dto

import com.be.havesomefun.domain.entity.InformationOfLocation
import java.time.format.DateTimeFormatter

class IOLDto {
    var id: Long? = null
    var time: String? = null
    var avgDecibel: Double = 0.0
    var avgLightLux: Double = 0.0
    var population: Double = 0.0
    var geohash7: String? = null
    var createdTime: String? = null

    companion object {
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        
        fun of (iol: InformationOfLocation) : IOLDto {
            val iolDto = IOLDto()
            iolDto.id = iol.id
            iolDto.time = iol.time
            iolDto.avgDecibel = iol.avgDecibel
            iolDto.avgLightLux = iol.avgLightLux
            iolDto.population = iol.population
            iolDto.geohash7 = iol.geohash7
            iolDto.createdTime = iol.createdTime?.format(dateFormatter).toString()
            return iolDto
        }

        fun of (io: InformationOfLocation, createdTime: String) : IOLDto {
            val iolDto = IOLDto()
            iolDto.id = io.id
            iolDto.time = io.time
            iolDto.avgDecibel = io.avgDecibel
            iolDto.avgLightLux = io.avgLightLux
            iolDto.population = io.population
            iolDto.geohash7 = io.geohash7
            iolDto.createdTime = createdTime
            return iolDto
        }
    }
}