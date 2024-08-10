package com.be.havesomefun.application.service

import ch.hsr.geohash.GeoHash
import com.be.havesomefun.domain.repository.IOLRepository
import com.be.havesomefun.domain.entity.InformationOfLocation
import com.opencsv.CSVReaderBuilder
import java.io.FileReader
import org.springframework.stereotype.Service

@Service
class IOLService(
    private val iolRepository: IOLRepository
) {
    private val BATCH_SIZE = 10000

    fun searchData(latitude: Double) : MutableList<InformationOfLocation> {
        val iolList = iolRepository.findAllByLatitude(latitude)
        return iolList
    }

    fun convertToGeohash(latitude: Double, longitude: Double) : String {
        return GeoHash.withCharacterPrecision(latitude, longitude, 7).toString()
    }

    fun getIOL(geoHash: String, populationRate: Double, luxRate: Double, decibelRate: Double) : InformationOfLocation {
        return iolRepository.findByGeohash(geoHash)
    }

    fun loadDataFromCSVAndSaveInBatch() {
        val reader = CSVReaderBuilder(FileReader("src/main/resources/merged_average_output.csv"))
            .withSkipLines(1)
            .build()

        var totalRows = 0
        val batch = mutableListOf<InformationOfLocation>()

        reader.use { csvReader ->
            csvReader.forEach { line ->
                batch.add(
                    InformationOfLocation(
                    time = line[0],
                    geohash = line[1],
                    population = line[2].toInt(),
                    latitude = line[3].toDouble(),
                    longitude = line[4].toDouble(),
                    si = line[5],
                    gu = line[6],
                    dong = line[7]
                )
                )
                if (batch.size >= BATCH_SIZE) {
                    totalRows += batch.size
                    iolRepository.saveAll(batch)
                    println("Saved $totalRows rows")
                    batch.clear()
                }

            }
        }
        if (batch.isNotEmpty()) {
            totalRows += batch.size
            iolRepository.saveAll(batch)
        }
    }
}
