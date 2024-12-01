package com.study.adcentofcode.y2023

import java.io.File

class Question08 {
    private var elements: MutableMap<String, Pair<String, String>> = mutableMapOf()
    private var steps: String = ""

    fun execute(filePath: String, part2: Boolean = false): Long {
        loadDataFromFile(filePath)

        val currentPath = elements.filter { it.key.last() == 'A' }.map { it.key }

        val numbers: MutableList<Long> = mutableListOf()
        currentPath.forEach { current ->
            var findLength = 0L
            var index = 0
            var next = getElement(steps[index], current)
            findLength++
            while (next.last() != 'Z' || (current == "AAA" && next != "ZZZ" )) {
                index++

                if (index >= steps.length) {
                    index = 0
                }

                next = getElement(steps[index], next)
                findLength++
            }

            if (!part2 && current == "AAA") return findLength

            numbers.add(findLength)
        }

        return findLcmFromNumbers(numbers)
    }

    private fun getElement(step: Char, currentKey: String): String = when (step) {
        'L' -> elements[currentKey]!!.first
        'R' -> elements[currentKey]!!.second
        else -> throw Exception("Invalid Step!")
    }

    private fun loadDataFromFile(filePath: String) {
        val reader = File(filePath).inputStream().bufferedReader()

        steps = reader.readLine()
        reader.readLine()

        var line: String? = reader.readLine()
        while (line != null) {
            val parts = line.split("=")
            val sides = parts[1].trim().split(",")

            val key = parts[0].trim()
            val left = sides[0].replace("(", "").trim()
            val right = sides[1].replace(")", "").trim()

            elements[key] = Pair(left, right)
            line = reader.readLine()
        }
    }

    private fun findLcmFromNumbers(numbers: List<Long>): Long {
        return numbers.fold(1L) { sum, current -> findLcm(sum, current)}
    }

    private fun findLcm(a: Long,b : Long): Long = a * b / calculateGcd(a, b)

    private fun calculateGcd(a: Long, b : Long): Long {
        var gcd = 1L
        var i = 1L
        while (i <= a && i <= b) {
            if (a % i == 0L && b % i == 0L)
                gcd = i
            ++i
        }
        return gcd
    }
}