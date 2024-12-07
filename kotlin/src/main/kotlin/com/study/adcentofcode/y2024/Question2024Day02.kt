package com.study.adcentofcode.y2024

import java.io.File
import kotlin.math.abs

@OptIn(ExperimentalStdlibApi::class)
class Question2024Day02 {
    fun execute(filePath: String, isPart2: Boolean = false): String = File(filePath).inputStream().bufferedReader()
        .lines()
        .map { splitToInt(it, " ") }
        .filter { isReportSafe(it, isPart2) }
        .count()
        .toString()

    private fun isReportSafe(report: List<Int>, withTolerate: Boolean = false): Boolean {
        var lastIsPositive: Boolean? = null
        return (1..< report.size).all {
            val difference = report[it - 1] - report[it]
            val isPositive = difference > 0
            isSafe(difference, isPositive, lastIsPositive).apply { lastIsPositive = isPositive } ||
                    withTolerate && checkTolerateSingleBadLevel(it, report)
        }
    }

    private fun checkTolerateSingleBadLevel(index: Int, report: List<Int>): Boolean = (index - 2..index)
        .filter { it >= 0 }
        .any { isReportSafe(dropItem(report, it)) }

    private fun isSafe(difference: Int, isPositive: Boolean, lastIsPositive: Boolean?): Boolean =
        abs(difference) in 1 .. 3 && difference != 0 && (lastIsPositive == null || lastIsPositive == isPositive)

    private fun splitToInt(text: String, separator: String): List<Int> = text.split(separator).map { it.toInt() }

    private fun dropItem(list: List<Int>, index: Int): List<Int> = list.filterIndexed { i, _ -> i != index }
}