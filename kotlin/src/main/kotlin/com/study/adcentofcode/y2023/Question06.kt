package com.study.adcentofcode.y2023

import java.io.File

class Question06 {
    private var times: List<Long> = listOf()
    private var distances: List<Long> = listOf()

    fun execute(filePath: String, isPart2: Boolean = false): Int {
        loadDataFromFile(filePath, isPart2)
        return times.foldIndexed(1) { index, sum, time ->
            sum * calculateWinCount(time, distances[index])
        }
    }

    private fun calculateWinCount(time: Long, distance: Long): Int {
        var count = 0
        for (i in 0 .. time) {
            val speed = i * 1
            val duration = time - i
            val totalDistance = speed * duration
            if (totalDistance > distance) count++
        }
        return count
    }

    private fun loadDataFromFile(filePath: String, shouldJoinNumbers: Boolean) {
        val reader = File(filePath).inputStream().bufferedReader()

        val timeLine = reader.readLine()
        val distanceLine = reader.readLine()

        if (shouldJoinNumbers) {
            times = listOf(timeLine.split(":")[1].trim().split("\\s+".toRegex())
                .fold(""){ sum, current -> sum + current }.toLong())

            distances = listOf(distanceLine.split(":")[1].trim().split("\\s+".toRegex())
                .fold(""){ sum, current -> sum + current }.toLong())
        } else {
            times = timeLine.split(":")[1].trim().split("\\s+".toRegex()).map { it.toLong() }

            distances = distanceLine.split(":")[1].trim().split("\\s+".toRegex()).map { it.toLong() }
        }
    }
}