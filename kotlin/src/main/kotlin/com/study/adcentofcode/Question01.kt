package com.study.adcentofcode

import java.io.File

class Question01 {
    private val numberMap: Map<String, Int> = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )
    private val numbers: MutableList<Pair<String, Int>> = mutableListOf()

    fun execute(filePath: String): String {
        var result = 0
        File(filePath).inputStream().bufferedReader().forEachLine {
            result += runLine(it)
        }
        return result.toString()
    }

    private fun runLine(line: String): Int {
        var first: Int? = null
        var second: Int? = null

        var selected: MutableList<Pair<String, Int>> = numbers

        line.forEach { character ->
            val valueInt = character.toIntOrNull()

            if (valueInt != null) {
                if (first == null) first = valueInt
                else second = valueInt

                selected = mutableListOf()
            } else {
                selected = selected.filter { item ->
                    if (item.second < item.first.length) {
                        item.first[item.second] == character
                    } else true
                }.map {
                    Pair(it.first, it.second + 1)
                } as MutableList

                numberMap.keys.filter { key ->
                    key[0] == character
                }.forEach {
                    selected.add(Pair(it, 1))
                }

                selected = selected.filter { item ->
                    val size = item.first.count()
                    val index = item.second
                    if (index >= size) {
                        val v = numberMap[item.first]
                        if (first == null) first = v
                        else second = v
                    }
                    true
                } as MutableList
            }
        }

        return first?.let {
            it * 10 + (if (second != null) second!! else it)
        } ?: 0
    }

    private fun Char.toIntOrNull(): Int? = try {
        digitToInt()
    } catch(e: Exception) {
        null
    }
}