package com.be.havesomefun.presentation.controller

import com.be.havesomefun.application.service.IOLService
import com.be.havesomefun.domain.entity.InformationOfLocation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class IOLController(
    private val iolService: IOLService
) {
    @GetMapping("/location/info")
    fun getLocationInformation(
        @RequestParam latitude: Double,
        @RequestParam longitude: Double,
        @RequestParam(required = false) time: String,
        @RequestParam(required = false) populationRate: Double,
        @RequestParam(required = false) luxRate: Double,
        @RequestParam(required = false) decibelRate: Double,
    ) : ResponseEntity<InformationOfLocation> {
        val geoHash = iolService.convertToGeohash(latitude, longitude)
        val response = iolService.getIOL(geoHash)

        return ResponseEntity.ok(response)
    }


    @PostMapping("/saveData")
    fun saveData() {
        iolService.loadDataFromCSVAndSaveInBatch()
    }

    @GetMapping("/getData")
    fun search(@RequestParam latitude: Double) : ResponseEntity<MutableList<InformationOfLocation>> {
        val response = iolService.searchData(latitude)

        return ResponseEntity.ok(response)
    }

}