package com.be.havesomefun.application.service

import ch.hsr.geohash.GeoHash
import com.be.havesomefun.domain.repository.IOLRepository
import com.be.havesomefun.domain.entity.InformationOfLocation
import com.opencsv.CSVReaderBuilder
import java.io.FileReader
import org.springframework.stereotype.Service
import java.io.InputStreamReader

@Service
class IOLService(
    private val iolRepository: IOLRepository
) {
    private val BATCH_SIZE = 10000

    fun convertToGeohash(latitude: Double, longitude: Double) : String {
        println(GeoHash.withCharacterPrecision(latitude, longitude, 7).toBase32())
        return GeoHash.withCharacterPrecision(latitude, longitude, 7).toBase32()
    }

    fun getIOLById(id: Long) : InformationOfLocation {
        return iolRepository.findById(id).get()
    }

    fun getIOL(
        time: String,
        minDecibel: Double,
        maxDecibel: Double,
        minLux: Double,
        maxLux: Double,
        minPopulation: Double,
        maxPopulation: Double,
        geohash7: String
    ) : List<InformationOfLocation> {
        return iolRepository.findByFilters(
            time = time,
            minDecibel = minDecibel,
            maxDecibel = maxDecibel,
            minLux = minLux,
            maxLux = maxLux,
            minPopulation = minPopulation,
            maxPopulation = maxPopulation,
            geohash7 = geohash7
        )
    }

    fun loadDataFromCSVAndSaveInBatch() {

        val inputStream = this.javaClass.classLoader.getResourceAsStream("merged_average_output.csv")
        if (inputStream == null) {
            println("Cannot find resource: merged_average_output.csv")
            return
        }
        val reader = CSVReaderBuilder(InputStreamReader(inputStream))
            .withSkipLines(1)
            .build()

        var totalRows = 0
        val batch = mutableListOf<InformationOfLocation>()

        reader.use { csvReader ->
            csvReader.forEach { line ->
                batch.add(
                    InformationOfLocation(
                    time = line[0],
                    avgDecibel = line[1].toDouble(),
                    avgLightLux = line[2].toDouble(),
                    population = line[3].toDouble(),
                    geohash7 = line[4],
                    )
                )
                if (batch.size >= BATCH_SIZE) {
                    println("Saving batch of ${batch.size} rows")
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
