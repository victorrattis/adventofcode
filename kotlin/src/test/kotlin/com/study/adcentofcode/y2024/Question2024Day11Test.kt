package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Question2024Day11Test {
	@Test
	fun testSample01() {
		val result = Question2024Day11().execute("src/test/resources/y2024/day11-sample01.txt")
		assertEquals("55312", result)
	}

	@Test
	fun testInput01() {
		val result = Question2024Day11().execute("src/test/resources/y2024/day11-input01.txt")
		assertEquals("199986", result)
	}

	@Test
	fun testInput01Part2() {
		val result = Question2024Day11().execute("src/test/resources/y2024/day11-input01.txt", isPart2 = true)
		assertEquals("236804088748754", result)
	}
}