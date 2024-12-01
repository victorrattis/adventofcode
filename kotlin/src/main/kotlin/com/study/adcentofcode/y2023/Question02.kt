package com.study.adcentofcode.y2023

import java.io.File

class Question02 {
    // only 12 red cubes, 13 green cubes, and 14 blue cubes
    private val redLimit = 12
    private val greenLimit = 13
    private val blueLimit = 14

    fun execute(filePath: String): String {
        var result = 0
        File(filePath)
            .inputStream().bufferedReader().forEachLine {
                result += runLine(it)
            }
        return result.toString()
    }

    private fun runLine(line: String): Int {
        var isPossible = true
        val game = line.split(":")
        val subsetsOfCubes = game[1].split(";")
        subsetsOfCubes.forEach {
            val options = it.trim().split(", ")
            options.forEach { option ->
                val info = option.split(" ")
                when(info[1]) {
                    "red" -> {
                        if (info[0].toInt() > redLimit) isPossible = false
                    }
                    "green" -> {
                        if (info[0].toInt() > greenLimit) isPossible = false
                    }
                    "blue" -> {
                        if (info[0].toInt() > blueLimit) isPossible = false
                    }
                }
            }
        }

        return if (isPossible) {
            game[0].split(" ")[1].toInt()
        } else {
            0
        }
    }
}