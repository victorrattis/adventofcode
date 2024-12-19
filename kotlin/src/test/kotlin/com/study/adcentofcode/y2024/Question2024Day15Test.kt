package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Question2024Day15Test {
	@Test
	fun testSample01() {
		val result = Question2024Day15().execute("src/test/resources/y2024/day15-sample01.txt")
		Assertions.assertEquals("2028", result)
	}

	@Test
	fun testSample02() {
		val result = Question2024Day15().execute("src/test/resources/y2024/day15-sample02.txt")
		Assertions.assertEquals("10092", result)
	}

	@Test
	fun testInput01() {
		val result = Question2024Day15().execute("src/test/resources/y2024/day15-input01.txt")
		Assertions.assertEquals("1492518", result)
	}


	@Test
	fun testSample02Part2() {
		val result = Question2024Day15().execute("src/test/resources/y2024/day15-sample02.txt", isPart2 = true)
		Assertions.assertEquals("9021", result)
	}

}