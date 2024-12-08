package com.study.adcentofcode.y2024

import java.io.File
import kotlin.math.max
import kotlin.math.min

class Question2024Day01: Question() {
    override fun executeInput(input: String, isPart2: Boolean): String {
        return if(isPart2) executePart2(input) else executePart1(input)
    }

    private fun executePart1(input: String): String {
        val left: MutableList<Int> = mutableListOf()
        val right: MutableList<Int> = mutableListOf()
        input.split("\n").forEach {
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

    private fun executePart2(input: String): String {
        val left: MutableList<Int> = mutableListOf()
        val numbers: MutableMap<Int, Int> = mutableMapOf()
        input.split("\n").forEach { line ->
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