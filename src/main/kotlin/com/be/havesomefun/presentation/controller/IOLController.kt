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
    /**
     * 필터에 해당하는 geohash를 가져오는 API*/
    @GetMapping("/location/info")
    fun searchLocationInformationList(
        @RequestParam time: String,
        @RequestParam minPopulation: Double,
        @RequestParam maxPopulation: Double,
        @RequestParam minLux: Double,
        @RequestParam maxLux: Double,
        @RequestParam minDecibel: Double,
        @RequestParam maxDecibel: Double,
    ) : ResponseEntity<List<String>> {

        val iolList = iolService.getIOL(
            time = time,
            minDecibel = minDecibel,
            maxDecibel = maxDecibel,
            minLux = minLux,
            maxLux = maxLux,
            minPopulation = minPopulation,
            maxPopulation = maxPopulation,
            geohash7 = ""
        )
            .map { IOLDto.of(it) }
            .map { IOLResponse.of(it) }

        val response = iolList.map {it.geohash7 ?: ""}

        return ResponseEntity.ok(response)
    }

    /**
     * 필터링 된 geohash를 선택하고 정보를 가져오는 API*/
    @GetMapping("/location/geohash/info")
    fun getGeohashLocationInformation(
        @RequestParam geohash: String,
    ) : ResponseEntity<List<IOLResponse>> {

        val response : List<IOLResponse> = iolService.getIOL(
            time = "",
            minDecibel = 0.0,
            maxDecibel = 0.0,
            minLux = 0.0,
            maxLux = 0.0,
            minPopulation = 0.0,
            maxPopulation = 0.0,
            geohash7 = geohash
        )
            .map { IOLDto.of(it) }
            .map { IOLResponse.of(it)}

        return ResponseEntity.ok(response)
    }

    /**
     * 한 지점을 선택하고 정보를 가져오는 API*/
    @GetMapping("/location/spot/info")
    fun getLocationInformation(
        @RequestParam latitude: Double,
        @RequestParam longitude: Double,
        @RequestParam time: String,
    ) : ResponseEntity<List<IOLResponse>> {

        val geohash = iolService.convertToGeohash(latitude, longitude)
        val response : List<IOLResponse> = iolService.getIOL(
            time = time,
            minDecibel = 0.0,
            maxDecibel = 0.0,
            minLux = 0.0,
            maxLux = 0.0,
            minPopulation = 0.0,
            maxPopulation = 0.0,
            geohash7 = geohash
        )
            .map { IOLDto.of(it) }
            .map { IOLResponse.of(it)}

        return ResponseEntity.ok(response)
    }

    @PostMapping("/saveData")
    fun saveData() : ResponseEntity<Void> {
        iolService.loadDataFromCSVAndSaveInBatch()

        return ResponseEntity.created(URI.create("/")).build()
    }

}