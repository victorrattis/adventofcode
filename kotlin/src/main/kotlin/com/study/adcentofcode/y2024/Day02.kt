package com.study.adcentofcode.y2024

import java.io.File
import kotlin.math.abs

@OptIn(ExperimentalStdlibApi::class)
class Day02 {
    fun execute(filePath: String, isPart2: Boolean = false): String = File(filePath).inputStream().bufferedReader()
        .lines()
        .map { splitToInt(it, " ") }
        .filter { isReportSafe(it, isPart2) }
        .count()
        .toString()

    private fun isReportSafe(report: List<Int>, withTolerate: Boolean = false): Boolean {
        var lastSignal: Boolean? = null // true is positive and false is negative number.
        return (1..< report.size).all {
            val difference = report[it - 1] - report[it]
            val signal = isNumberPositive(difference)
            isSafe(difference, signal, lastSignal).apply { lastSignal = signal } ||
                    withTolerate && checkTolerateSingleBadLevel(it, report)
        }
    }

    private fun checkTolerateSingleBadLevel(index: Int, report: List<Int>): Boolean = (index - 2..index)
        .filter { it >= 0 }
        .any { isReportSafe(dropItem(report, it)) }

    private fun isSafe(diff: Int, signalDiff: Boolean, lastDiff: Boolean?): Boolean =
        abs(diff) in 1 .. 3 && diff != 0 && (lastDiff == null || lastDiff == signalDiff)

    private fun isNumberPositive(number: Int) = number > 0

    private fun splitToInt(text: String, separator: String): List<Int> = text.split(separator).map { it.toInt() }

    private fun dropItem(list: List<Int>, index: Int): List<Int> = list.filterIndexed { i, _ -> i != index }
}