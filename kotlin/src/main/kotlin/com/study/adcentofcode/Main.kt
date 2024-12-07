package com.study.adcentofcode

import com.study.adcentofcode.y2024.Question2024Day03
import com.study.adcentofcode.y2024.Question2024Day04

fun main(args: Array<String>) {
	Question2024Day03().measureExecution("src/test/resources/y2024/day03-input01.txt", isPart2 = false).let {
		println("Day 03 part 1: $it")
	}

	Question2024Day03().measureExecution("src/test/resources/y2024/day03-input01.txt", isPart2 = true).let {
		println("Day 03 part 2: $it")
	}

	Question2024Day04().measureExecution("src/test/resources/y2024/day04-input01.txt", isPart2 = false).let {
		println("Day 04 part 1: $it")
	}

    Question2024Day04().measureExecution("src/test/resources/y2024/day04-input01.txt", isPart2 = true).let {
        println("Day 04 part 2: $it")
    }
}
