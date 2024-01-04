package com.study.adcentofcode

import java.io.File

data class NumberPositionDetail(
    val value: Int,
    val start: Int,
    val end: Int,
    val line: Int
) {
    private fun inRange(index: Int): Boolean = index in start..end

    fun isAdj(line: Int, column: Int): Boolean {
        if (this.line == line - 1) {
            if(inRange(column)) return true
            if(inRange(column - 1)) return true
            if(inRange(column + 1)) return true
        }

        // middle
        if (this.line == line) {
            if(inRange(column - 1)) return true
            if(inRange(column + 1)) return true
        }

        // down
        if (this.line == line + 1) {
            if(inRange(column)) return true
            if(inRange(column - 1)) return true
            if(inRange(column + 1)) return true
        }

        return false
    }
}

data class SymbolDetail(
    val value: Char,
    val line: Int,
    val column: Int
)

class Question03 {
    private val numbers: MutableList<NumberPositionDetail> = mutableListOf()
    private val symbols: MutableList<SymbolDetail> = mutableListOf()

    fun execute(filePath: String): String {
        findNumberAndSymbolsInFile(filePath)

        var result = 0
        numbers.forEach { number ->
            if (hasSymbolAdjacent(number)) {
                result += number.value
            }
        }
        return result.toString()
    }

    fun executePt2(filePath: String): String {
        findNumberAndSymbolsInFile(filePath)

        var result = 0
        symbols.forEach { symbol ->
            if (symbol.value == '*') {
                var count = 0
                var gear = 1
                numbers.forEach { number ->
                    if (number.isAdj(symbol.line, symbol.column)) {
                        count++
                        gear *= number.value
                    }
                }
                if (count >= 2) {
                    result += gear
                }
            }
        }
        return result.toString()
    }

    private fun findNumberAndSymbolsInFile(filePath: String) {
        var line = 0
        File(filePath).inputStream().bufferedReader().forEachLine {
            runLine(it, line++)
        }
    }

    private fun hasSymbolAdjacent(number: NumberPositionDetail): Boolean {
        symbols.forEach { symbol ->
            if (number.isAdj(symbol.line, symbol.column)) return true
        }
        return false
    }

    private fun runLine(line: String, lineIndex: Int) {
        var startNumberIndex = -1
        var number: String? = null
        line.forEachIndexed { index, c ->
            if (c.toIntOrNull() != null) {
                if (startNumberIndex == -1) {
                    startNumberIndex = index
                    number = "$c"
                } else {
                    number += c
                }

            } else {
                if (startNumberIndex != -1) {
                    number?.toIntOrNull()?.let {
                        numbers.add(NumberPositionDetail(it, startNumberIndex, index - 1, lineIndex))
                    }
                    startNumberIndex = -1
                }
                if (c != '.') {
                    symbols.add(SymbolDetail(c, lineIndex, index))
                }
            }
        }

        if (startNumberIndex != -1) {
            number?.toIntOrNull()?.let {
                numbers.add(NumberPositionDetail(it, startNumberIndex, line.length - 1, lineIndex))
            }
            startNumberIndex = -1
        }
    }

    private fun Char.toIntOrNull(): Int? = try {
        digitToInt()
    } catch(e: Exception) {
        null
    }
}