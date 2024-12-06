package com.study.adcentofcode.y2024

import java.io.File
class Question2024Day03 {
    fun execute(filePath: String, isPart2: Boolean = false): String = File(filePath).inputStream().bufferedReader()
		.readText()
        .let { if (isPart2) findMulPatterns2(it) else findMulPatterns(it) }
		.map { Regex(NUMBER_REGEX).findAll(it).map { it.value.toInt() }.toList() }
		.sumOf { it.reduce { acc, s -> acc * s } }
		.toString()

    private fun findMulPatterns(text: String): List<String> =
        Regex(TWO_NUMBER_TO_MULTIPLY_REGEX).findAll(text).map { it.value }.toList()

    private fun findMulPatterns2(text: String): List<String> =
        Regex(TWO_NUMBER_TO_MULTIPLY_WITH_IGNORE_REGEX)
            .findAll(text)
            .let {
                var isIgnored = true
                it.filter { text ->
                    when (text.value) {
                        "don't()" -> {
                            isIgnored = false
                            false
                        }
                        "do()" -> {
                            isIgnored = true
                            false
                        }
                        else -> {
                            isIgnored
                        }
                    }
                }
            }
            .map { it.value }
            .toList()

    companion object {
        const val TWO_NUMBER_TO_MULTIPLY_REGEX = "mul\\([0-9]{1,3},[0-9]{1,3}\\)"
        const val TWO_NUMBER_TO_MULTIPLY_WITH_IGNORE_REGEX = "mul\\([0-9]{1,3},[0-9]{1,3}\\)|don't\\(\\)|do\\(\\)"
        const val NUMBER_REGEX = "[0-9]{1,3}"
    }
}