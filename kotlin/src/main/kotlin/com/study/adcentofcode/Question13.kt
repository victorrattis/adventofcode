package com.study.adcentofcode

import java.io.File

class Question13 {
    fun execute(filePath: String): Long {
        val patterns = loadDataFromFile(filePath)
        return patterns.fold(0L) { acc, pattern ->
            acc + calculatePattern(pattern)
        }
    }

    private fun calculatePattern(pattern: Array<String>): Int {
        var total = 0

        val column = checkMirror(pattern, pattern[0].length) { pattern, start, end ->
            isSameColumns(pattern, start, end)
        }
        if (column != -1) {
            total += column + 1
        }

        val row = checkMirror(pattern, pattern.size) { pattern, start, end ->
            isSameRows(pattern, start, end)
        }
        if (row != -1) {
            total += (row + 1) * 100
        }

        return total
    }

    private fun checkMirror(pattern: Array<String>, size: Int, isSame: (Array<String>, Int, Int) -> Boolean): Int {
        for(i in 1 until size) {
            var isMirror = false
            if (isSame(pattern, i, i -1)) {
                isMirror = true
                var j = 1
                while (i + j < size && i - 1 - j >= 0) {
                    if (!isSame(pattern, i + j, i - j - 1)) {
                        isMirror = false
                        break
                    }
                    j++
                }
            }
            if (isMirror) {
                return i - 1
            }
        }
        return -1
    }

    private fun isSameRows(
        pattern: Array<String>,
        first: Int,
        second: Int
    ): Boolean {
        for(i in 0 until pattern[0].length) {
            if (pattern[first][i] != pattern[second][i]) {
                return false
            }
        }
        return true
    }

    private fun isSameColumns(
        pattern: Array<String>,
        first: Int,
        second: Int
    ): Boolean {
        for(i in pattern.indices) {
            if (pattern[i][first] != pattern[i][second]) {
                return false
            }
        }
        return true
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