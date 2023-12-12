package com.study.adcentofcode

import java.io.File

class Question04 {
    fun execute(filePath: String, isPart2: Boolean = false): Int {
        val cards: MutableList<Int> = mutableListOf()
        File(filePath).inputStream().bufferedReader().forEachLine { line ->
            cards.add(calculateMatchCountInLine(line))
        }

        return if (!isPart2) {
            cards.fold(0) { sum, match ->
                sum + if (match > 0) 1 shl match - 1 else 0
            }
        } else {
            val copies = IntArray(cards.size){ 1 }
            cards.foldIndexed(0) { index, sum, match ->
                val start = index + 1
                var end = start + match
                if (end > copies.size) end = copies.size
                for (i in start until end) {
                    copies[i] += copies[index]
                }
                sum + copies[index]
            }
        }
    }

    private fun calculateMatchCountInLine(line: String): Int {
        val card = line.split(":")
        val numbers = card[1].split(" | ")
        val winningNumbers = numbers[0].trim().split("\\s+".toRegex()).map { it.toInt() }
        val numbersYouHave = numbers[1].trim().split("\\s+".toRegex()).map { it.toInt() }.toSet()
        return winningNumbers.intersect(numbersYouHave).size
    }
}