package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Question2024Day10Test {
	@Test
	fun testSample01() {
		val result = Question2024Day10().execute("src/test/resources/y2024/day10-sample01.txt")
		assertEquals("36", result)
	}

	@Test
	fun testInput01() {
		val result = Question2024Day10().execute("src/test/resources/y2024/day10-input01.txt")
		assertEquals("782", result)
	}
}