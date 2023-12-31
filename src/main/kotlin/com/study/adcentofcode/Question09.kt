package com.study.adcentofcode

import java.io.File

class Question09 {
    fun execute(filePath: String, part2: Boolean = false): Long {
        val historyOfValues = loadDataFromFile(filePath)

        // prediction of the next value in each history
        return historyOfValues.fold(0L) { sum, current ->
            sum + calculateNextValue(current)
        }
    }

    private fun calculateNextValue(values: List<Int>): Long {
        val sequences: MutableList<Array<Int>> = mutableListOf()
        var currentValues: Array<Int> = values.toTypedArray()
        do {
            currentValues = Array(currentValues.size - 1) { index ->
                currentValues[index + 1] - currentValues[index]
            }.also {
                sequences.add(it)
            }

            val isAllZero = currentValues.fold(true) { sum, item ->
                sum && item == 0
            }
        } while (!isAllZero)

        return values.last() + sequences.fold(0L) { sum, sequence ->
            sum + sequence.last()
        }
    }

    private fun loadDataFromFile(filePath: String): List<List<Int>> {
        val values = mutableListOf<List<Int>>()
        File(filePath).inputStream().bufferedReader().forEachLine { line ->
            values.add(line.split(" ").map { it.trim().toInt() })
        }
        return values.toList()
    }
}