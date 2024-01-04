package com.study.adcentofcode

import java.io.File

class Question02pt2 {
    fun execute(filePath: String): String {
        var result = 0
        File(filePath)
            .inputStream().bufferedReader().forEachLine {
                result += runLine(it)
            }
        return result.toString()
    }

    private fun runLine(line: String): Int {
        var redLimit = 0
        var greenLimit = 0
        var blueLimit = 0

        val game = line.split(":")
        val subsetsOfCubes = game[1].split(";")
        subsetsOfCubes.forEach {
            val options = it.trim().split(", ")
            options.forEach { option ->
                val info = option.split(" ")
                when(info[1]) {
                    "red" -> {
                        if (info[0].toInt() > redLimit) redLimit = info[0].toInt()
                    }
                    "green" -> {
                        if (info[0].toInt() > greenLimit) greenLimit = info[0].toInt()
                    }
                    "blue" -> {
                        if (info[0].toInt() > blueLimit) blueLimit = info[0].toInt()
                    }
                }
            }
        }

        val isPossible = redLimit > 0 && greenLimit > 0 && blueLimit > 0
        return if (isPossible) {
            redLimit * greenLimit * blueLimit
        } else {
            0
        }
    }
}