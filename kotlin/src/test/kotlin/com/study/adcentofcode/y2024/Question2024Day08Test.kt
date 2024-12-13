package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Question2024Day08Test {
	@Test
	fun testSample01() {
		val result = Question2024Day08().execute("src/test/resources/y2024/day08-sample01.txt")
		assertEquals("14", result)
	}

	@Test
	fun testSample02() {
		val result = Question2024Day08().execute("src/test/resources/y2024/day08-sample02.txt")
		assertEquals("2", result)
	}

	@Test
	fun testSample03() {
		val result = Question2024Day08().execute("src/test/resources/y2024/day08-sample03.txt")
		assertEquals("4", result)
	}

	@Test
	fun testInput01() {
		val result = Question2024Day08().execute("src/test/resources/y2024/day08-input01.txt")
		assertEquals("329", result)
	}

	@Test
	fun testSample04Part2() {
		val result = Question2024Day08().execute("src/test/resources/y2024/day08-sample04.txt", isPart2 = true)
		assertEquals("9", result)
	}

	@Test
	fun testSample01Part2() {
		val result = Question2024Day08().execute("src/test/resources/y2024/day08-sample01.txt", isPart2 = true)
		assertEquals("34", result)
	}

	@Test
	fun testInput01Part2() {
		val result = Question2024Day08().execute("src/test/resources/y2024/day08-input01.txt", isPart2 = true)
		assertEquals("1190", result)
	}
}