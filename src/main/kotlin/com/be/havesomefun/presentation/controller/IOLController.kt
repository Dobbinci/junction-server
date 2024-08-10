package com.be.havesomefun.presentation.controller

import com.be.havesomefun.application.dto.IOLDto
import com.be.havesomefun.application.service.IOLService
import com.be.havesomefun.domain.entity.InformationOfLocation
import com.be.havesomefun.presentation.response.IOLResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api")
class IOLController(
    private val iolService: IOLService
) {
    @GetMapping("/location/info")
    fun getLocationInformation(
        @RequestParam latitude: Double,
        @RequestParam longitude: Double,
        @RequestParam time: String,
        @RequestParam minPopulation: Double,
        @RequestParam maxPopulation: Double,
        @RequestParam minLux: Double,
        @RequestParam maxLux: Double,
        @RequestParam minDecibel: Double,
        @RequestParam maxDecibel: Double,
    ) : ResponseEntity<MutableList<IOLResponse>> {

        val geoHash = iolService.convertToGeohash(latitude, longitude)
        val response : MutableList<IOLResponse> = iolService.getIOL(
            time = time,
            minDecibel = minDecibel,
            maxDecibel = maxDecibel,
            minLux = minLux,
            maxLux = maxLux,
            minPopulation = minPopulation,
            maxPopulation = maxPopulation,
            geohash7 = geoHash
        )
            .map { IOLDto.of(it) }
            .map { IOLResponse.of(it)}
            .toMutableList()

        return ResponseEntity.ok(response)
    }

    @PostMapping("/saveData")
    fun saveData() : ResponseEntity<Void> {
        iolService.loadDataFromCSVAndSaveInBatch()

        return ResponseEntity.created(URI.create("/")).build()
    }

}