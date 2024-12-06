package com.study.adcentofcode.y2024

import java.io.File
import kotlin.math.max
import kotlin.math.min

class Day01 {
    fun execute(filePath: String): String {
        val left: MutableList<Int> = mutableListOf()
        val right: MutableList<Int> = mutableListOf()
        File(filePath).inputStream().bufferedReader().forEachLine {
            it.split("   ").let {
                left.add(it[0].toInt())
                right.add(it[1].toInt())
            }
        }

        left.sort()
        right.sort()
        var result = 0
        for (i in left.indices) {
            result += max(left[i], right[i]) - min(left[i], right[i])
        }
        return result.toString()
    }

    fun execute2(filePath: String): String {
        val left: MutableList<Int> = mutableListOf()
        val numbers: MutableMap<Int, Int> = mutableMapOf()
        File(filePath).inputStream().bufferedReader().forEachLine { line ->
            line.split("   ").let {
                left.add(it[0].toInt())
                it[1].toInt().let {
                    numbers[it] = (numbers[it] ?: 0) + 1
                }
            }
        }
        var result = 0
        left.forEach {
            result += (it * (numbers[it] ?: 0))
        }
        return result.toString()
    }
}