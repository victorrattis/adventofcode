package com.study.adcentofcode.y2023

import java.io.File

class Question13 {
    fun execute(filePath: String, part2: Boolean = false): Long {
        val patterns = loadDataFromFile(filePath)
        return patterns.fold(0L) { acc, pattern ->
            acc + calculatePattern(pattern, part2)
        }
    }

    private fun calculatePattern(pattern: Array<String>, part2: Boolean): Int {
        var total = 0
        val limit = if (part2) 1 else 0

        val column = checkFixedMirror(pattern, pattern[0].length, limit) { pattern, start, end ->
            countDifferentColumns(pattern, start, end)
        }
        if (column != -1) {
            total += column + 1
        }

        val row = checkFixedMirror(pattern, pattern.size, limit) { pattern, start, end ->
            countDifferentRows(pattern, start, end)
        }
        if (row != -1) {
            total += (row + 1) * 100
        }

        return total
    }

    private fun checkFixedMirror(
        pattern: Array<String>,
        size: Int,
        limit: Int = 0,
        isSame: (Array<String>, Int, Int) -> Int
    ): Int {
        for(i in 1 until size) {
            var isMirror = false
            val diff = isSame(pattern, i, i -1)
            if (diff <= limit) {
                var fix  = limit - diff
                isMirror = true
                var j = 1
                while (i + j < size && i - 1 - j >= 0) {
                    val count = isSame(pattern, i + j, i - j - 1)
                    if (fix - count < 0) {
                        isMirror = false
                        break
                    } else {
                        fix -= count
                    }
                    j++
                }
                if (fix != 0) {
                    isMirror = false
                }
            }
            if (isMirror) {
                return i - 1
            }
        }
        return -1
    }

    private fun countDifferentRows(
        pattern: Array<String>,
        first: Int,
        second: Int
    ): Int {
        var row = 0
        for(i in 0 until pattern[0].length) {
            if (pattern[first][i] != pattern[second][i]) {
                row++
            }
        }
        return row
    }

    private fun countDifferentColumns(
        pattern: Array<String>,
        first: Int,
        second: Int
    ): Int {
        var column = 0
        for(i in pattern.indices) {
            if (pattern[i][first] != pattern[i][second]) {
                column++
            }
        }
        return column
    }

    private fun loadDataFromFile(filePath: String): Array<Array<String>> {
        val patterns = mutableListOf<Array<String>>()
        var lines = mutableListOf<String>()
        File(filePath).inputStream().bufferedReader().forEachLine { line ->
            if (line.isEmpty()) {
                patterns.add(lines.toTypedArray())
                lines = mutableListOf()
            } else {
                lines.add(line)
            }
        }
        patterns.add(lines.toTypedArray())
        return patterns.toTypedArray()
    }
}