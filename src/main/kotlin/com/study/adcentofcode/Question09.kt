package com.study.adcentofcode

import java.io.File

class Question09 {
    fun execute(filePath: String, part2: Boolean = false): Long {
        val historyOfValues = loadDataFromFile(filePath)

        // prediction of the next value in each history
        return historyOfValues.fold(0L) { sum, current ->
            sum + calculateNextValue(current, part2)
        }
    }

    private fun calculateNextValue(values: List<Int>, part2: Boolean): Long {
        val sequences: MutableList<Array<Int>> = mutableListOf()
        var currentValues: Array<Int> = values.toTypedArray()
        do {
            currentValues = Array(currentValues.size - 1) { index ->
                currentValues[index + 1] - currentValues[index]
            }.also {
                sequences.add(it)
            }

            val isAllZero = currentValues.fold(true) { sum, value ->
                sum && value == 0
            }
        } while (!isAllZero)

        return if (!part2) {
            values.last() + sequences.foldRight(0L) { sequence, sum ->
                sum + sequence.last()
            }
        } else {
            values.first() - sequences.foldRight(0L) { sequence, sum ->
                sequence.first() - sum
            }
        }
    }

    private fun loadDataFromFile(filePath: String): List<List<Int>> {
        val values = mutableListOf<List<Int>>()
        File(filePath).inputStream().bufferedReader().forEachLine { line ->
            values.add(line.split("\\s+".toRegex()).map { it.trim().toInt() })
        }
        return values.toList()
    }
}