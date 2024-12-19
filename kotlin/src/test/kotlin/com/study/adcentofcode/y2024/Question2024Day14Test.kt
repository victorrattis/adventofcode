package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Question2024Day14Test {
	@Test
	fun testSample01() {
		val result = Question2024Day14().execute("src/test/resources/y2024/day14-sample01.txt")
		Assertions.assertEquals("12", result)
	}

	@Test
	fun testInput01() {
		val result = Question2024Day14().execute("src/test/resources/y2024/day14-input01.txt")
		Assertions.assertEquals("231221760", result)
	}

	@Test
	fun testInput01Part2() {
		val result = Question2024Day14().execute("src/test/resources/y2024/day14-input01.txt", isPart2 = true)
		Assertions.assertEquals("", result)
	}
}