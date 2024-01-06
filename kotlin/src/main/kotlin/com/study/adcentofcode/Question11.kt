package com.study.adcentofcode

import java.io.File
import kotlin.math.abs

class Question11 {
    fun execute(filePath: String, expansion: Int = 2): Long {
        val space = loadDataFromFile(filePath)
        val galaxies = findGalaxies(space, expansion-1)
        return calculateAllShortestDistances(galaxies)
    }

    private fun calculateAllShortestDistances(
        galaxies: Array<Galaxy>
    ): Long {
        var sum = 0L
        for (i in 0 until galaxies.size - 1) {
            for (j in i + 1 until galaxies.size) {
                sum += abs(galaxies[i].x - galaxies[j].x) +
                        abs(galaxies[i].y - galaxies[j].y)
            }
        }
        return sum
    }

    private fun findGalaxies(
        space: Array<String>,
        expansion: Int = 1
    ): Array<Galaxy> {
        val markedLine = Array (space.size) { false }
        val markedColumn = Array (space[0].length) { false }

        val galaxies = mutableListOf<Galaxy>()
        space.forEachIndexed { x, line ->
            line.forEachIndexed { y, tile ->
                if (tile == '#') {
                    markedLine[x] = true
                    markedColumn[y] = true
                    galaxies.add(Galaxy(x, y))
                }
            }
        }

        // calculate space expansion
        var acc = 0
        val weightLine = markedLine.map { hasGalaxy ->
            if (!hasGalaxy) acc += expansion
            acc
        }
        acc = 0
        val weightColumn = markedColumn.map { hasGalaxy ->
            if (!hasGalaxy) acc += expansion
            acc
        }

        return galaxies.map {
            it.copy(
                x = it.x + weightLine[it.x],
                y = it.y + weightColumn[it.y]
            )
        }.toTypedArray()
    }

    private fun loadDataFromFile(filePath: String): Array<String> {
        val lines = mutableListOf<String>()
        File(filePath).inputStream().bufferedReader().forEachLine { line ->
            lines.add(line)
        }
        return lines.toTypedArray()
    }

    private data class Galaxy(
        val x: Int,
        val y: Int
    )
}