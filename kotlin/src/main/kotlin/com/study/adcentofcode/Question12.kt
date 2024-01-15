package com.study.adcentofcode

import java.io.File

class Question12 {
    fun execute(filePath: String, repeat: Int = 1): Long {
        val lines = loadDataFromFile(filePath)
        return lines.fold(0L) { acc, line ->
            acc + calculateArrangement(line, repeat)
        }
    }

    private fun solutionDFA(text: String, numbers: Array<Int>): Long {
        val states = numbers.fold(".") { acc, number ->
            acc + "#".repeat(number) + "."
        }
        var statesDict = mapOf(0 to 1L)
        var newDict = mutableMapOf<Int, Long>()

        for (char in text) {
            for (state in statesDict) {
                if (char == '?') {
                    if (state.key + 1 < states.length) {
                        newDict[state.key + 1] =
                            (newDict[state.key + 1] ?: 0) + (statesDict[state.key] ?: 0)
                    }
                    if (states[state.key] == '.') {
                        newDict[state.key] =
                            (newDict[state.key] ?: 0) + (statesDict[state.key] ?: 0)
                    }
                } else if (char == '.') {
                    if (state.key + 1 < states.length && states[state.key + 1] == '.') {
                        newDict[state.key + 1] =
                            (newDict[state.key + 1] ?: 0) + (statesDict[state.key] ?: 0)
                    }
                    if (states[state.key] == '.') {
                        newDict[state.key] =
                            (newDict[state.key] ?: 0) + (statesDict[state.key] ?: 0)
                    }
                } else if (char == '#') {
                    if (state.key + 1 < states.length && states[state.key + 1] == '#') {
                        newDict[state.key + 1] =
                            (newDict[state.key + 1] ?: 0) + (statesDict[state.key] ?: 0)
                    }
                }
            }
            statesDict = newDict
            newDict = mutableMapOf()
        }

        return (statesDict[states.length - 1] ?: 0) + (statesDict[states.length - 2] ?: 0)
    }


    private fun calculateArrangement(line: String, repeat: Int): Long {
        val parts = line.split("\\s+".toRegex())

        var textGroups = parts[1]
        if (repeat > 1) {
            for (i in 0 until repeat - 1)
                textGroups = "$textGroups,${parts[1]}"
        }
        val groups = textGroups.split(",").map { it.toInt() }

        var text = parts[0]
        if (repeat > 1) {
            for (i in 0 until repeat - 1)
                text = "$text?${parts[0]}"
        }

//        combineAllPossibilities(text, groups.toTypedArray())
//        return combinations.size.toLong()

        return solutionDFA(text, groups.toTypedArray())
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