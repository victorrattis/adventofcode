package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Question2024Day09Test {
	@Test
	fun testSample01() {
		val result = Question2024Day09().execute("src/test/resources/y2024/day09-sample01.txt")
		assertEquals("1928", result)
	}

	@Test
	fun testInput01() {
		val result = Question2024Day09().execute("src/test/resources/y2024/day09-input01.txt")
		assertEquals("6310675819476", result)
	}

	@Test
	fun testSample01Part2() {
		val result = Question2024Day09().execute("src/test/resources/y2024/day09-sample01.txt", isPart2 = true)
		assertEquals("2858", result)
	}
}