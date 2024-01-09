package com.study.adcentofcode

import java.io.File

class Question12 {
    constructor()

    fun execute(filePath: String, repeat: Int = 1): Long {
        val lines = loadDataFromFile(filePath)
//        calculateArrangement(lines[5], 1)
//        return 0L

        return lines.fold(0L) { acc, line ->
            acc + calculateArrangement(line, repeat)
        }
    }

    private fun calculateArrangement(line: String, repeat: Int): Long {
        val parts = line.split("\\s+".toRegex())

        var textGroups = parts[1]
        if (repeat > 1) {
            for (i in 0 until repeat - 1)
                textGroups = "$textGroups,1,${parts[1]}"
            println("$textGroups")

        }
        val groups = textGroups.split(",").map { it.toInt() }

        var text = parts[0]
        if (repeat > 1) {
            for (i in 0 until repeat - 1)
                text = "$text?${parts[0]}"
            println(text)

        }

        combineAllPossibilities(text, groups.toTypedArray())
        println("$text = ${combinations.size}")
        return combinations.size.toLong()
    }

    private fun combineAllPossibilities(sequences: String, groups: Array<Int>) {
        val elements = arrayOf('.', '#')
        combinations = mutableSetOf()
        combineStringFromElements(sequences, groups, elements, "", sequences.length)
    }

    private fun combineStringFromElements(
        input: String,
        groups: Array<Int>,
        elements: Array<Char>,
        currentString: String,
        maxLength: Int
    ) {
        if (currentString.length == maxLength) {
            checkCombination(currentString, groups)
            return
        }
        val tile = input[if(currentString.isEmpty()) 0 else currentString.length]
        if (tile == '?')
            for (element in elements) {
                combineStringFromElements(input, groups, elements, currentString + element, maxLength)
            }
        else
            combineStringFromElements(input, groups, elements, currentString + tile, maxLength)
    }

    private var combinations: MutableSet<String> = mutableSetOf()

    private fun checkCombination(text: String, groups: Array<Int>) {

        var index = 0
        var count = 0
        var all = true
        for (tile in text) {
            if (tile == '#') {
                count++
            } else if (tile == '.') {
                if (count > 0) {
                    val result = index < groups.size && count == groups[index]
                    index++
                    all = all && result
                    count = 0
                }
            }
        }

        if (count > 0) {
            val result = index < groups.size && count == groups[index]
            index++
            all = all && result
        }

        if (all && index == groups.size) {
            combinations.add(text)
        }
    }

    private fun loadDataFromFile(filePath: String): Array<String> {
        val lines = mutableListOf<String>()
        File(filePath).inputStream().bufferedReader().forEachLine { line ->
            lines.add(line)
        }
        return lines.toTypedArray()
    }
}