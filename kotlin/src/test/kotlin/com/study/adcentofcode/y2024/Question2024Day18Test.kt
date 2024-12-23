package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Question2024Day18Test {
	@Test
	fun testSample01() {
		val result = Question2024Day18().execute("src/test/resources/y2024/day18-sample01.txt")
		Assertions.assertEquals("22", result)
	}

	@Test
	fun testInput01() {
		val result = Question2024Day18().execute("src/test/resources/y2024/day18-input01.txt")
		Assertions.assertEquals("384", result)
	}

	@Test
	fun testSample01Part2() {
		val result = Question2024Day18().execute("src/test/resources/y2024/day18-sample01.txt", isPart2 = true)
		Assertions.assertEquals("6,1", result)
	}

	@Test
	fun testInput01Part2() {
		val result = Question2024Day18().execute("src/test/resources/y2024/day18-input01.txt", isPart2 = true)
		Assertions.assertEquals("36,10", result)
	}
}