package com.study.adcentofcode

import com.study.adcentofcode.y2024.*

fun main(args: Array<String>) {
	for (i in 0..10) {
		Question2024Day09().measureExecution("src/test/resources/y2024/day09-input01.txt", isPart2 = false).let {
			println("Day 09 part 1: $it")
		}

//		Question2024Day06().measureExecution("src/test/resources/y2024/day06-input01.txt", isPart2 = true).let {
//			println("Day 06 part 2: $it")
//		}
	}
}
