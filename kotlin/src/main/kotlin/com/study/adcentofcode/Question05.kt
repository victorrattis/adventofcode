package com.study.adcentofcode

import java.io.BufferedReader
import java.io.File

class Question05 {

    private var seeds: List<Long> = listOf()
    private var maps: MutableMap<String, MapDetail> = mutableMapOf()
    private var lowestLocationNumber: Long = -1L

    fun execute(filePath: String, isPart2: Boolean = false): Long {
        loadAllLinesInTheFile(filePath)
        if (isPart2) {
            val end = seeds.size - 1
            for (i in 0..end step 2) {
                val rangeEnd = seeds[i] + seeds[i + 1]
                for (seed in seeds[i]..rangeEnd) {
                    val locationNumber = findLocationNumber(seed)
                    if (lowestLocationNumber == -1L || (lowestLocationNumber > locationNumber)) {
                        lowestLocationNumber = locationNumber
                    }
                }
            }
        } else {
            seeds.forEach { seed ->
                val locationNumber = findLocationNumber(seed)
                if (lowestLocationNumber == -1L || (lowestLocationNumber > locationNumber)) {
                    lowestLocationNumber = locationNumber
                }
            }
        }
        return lowestLocationNumber
    }

    private fun findLocationNumber(seed: Long): Long {
        var source = "seed"
        var sourceValue = seed
        do {
            maps[source]?.let {
                var destValue = it.calculate(sourceValue)
                source = destValue.first
                sourceValue = destValue.second
            }
        } while (source != "location")
        return sourceValue
    }

    private fun loadAllLinesInTheFile(filePath: String) {
        val reader = File(filePath).inputStream().bufferedReader()
        seeds = reader.readLine().split(":")[1].trim().split(" ").map { it.toLong() }

        var line: String? = reader.readLine()
        while (line != null) {
            if (line.endsWith("map:")) {
                loadListOfMaps(line, reader)
            }
            line = reader.readLine()
        }
    }

    private fun loadListOfMaps(lineWithMap: String, reader: BufferedReader) {
        val mapSplits = lineWithMap.split(" ")[0].split("-")
        val source = mapSplits[0]
        val destination = mapSplits[2]

        val ranges = mutableListOf<MapRangeDetail>()
        var line = reader.readLine()
        while (!line.isNullOrEmpty()) {
            val values = line.split(" ").map { it.toLong() }
            ranges.add(MapRangeDetail(values[1], values[0], values[2]))
            line = reader.readLine()
        }

        maps[source] = MapDetail(source, destination, ranges)
    }

    data class MapDetail(
        val source: String,
        val destination: String,
        val ranges: List<MapRangeDetail>
    ) {
        fun calculate(seed: Long): Pair<String, Long> {
            var destNumber = -1L
            ranges.forEach { range ->
                val end = range.sourceStart + range.length - 1
                if (seed in range.sourceStart ..end) {
                    val index =  seed - range.sourceStart
                    destNumber = range.destinationStart + index
                }
            }
            if (destNumber == -1L) {
                destNumber = seed
            }
            return Pair(destination, destNumber)
        }
    }

    data class MapRangeDetail(
        val sourceStart: Long,
        val destinationStart: Long,
        val length: Long
    )
}