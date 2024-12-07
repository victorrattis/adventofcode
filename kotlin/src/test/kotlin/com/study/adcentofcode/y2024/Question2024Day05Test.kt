package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Question2024Day05Test {
	@Test
	fun test01() {
		val result = Question2024Day05().execute("src/test/resources/y2024/day05-sample01.txt")
		assertEquals("143", result)
	}

	@Test
	fun testInput01() {
		val result = Question2024Day05().execute("src/test/resources/y2024/day05-input01.txt")
		assertEquals("6034", result)
	}

	@Test
	fun test01Part2() {
		val result = Question2024Day05().execute("src/test/resources/y2024/day05-sample01.txt", isPart2 = true)
		assertEquals("123", result)
	}

	@Test
	fun testInput01Part2() {
		val result = Question2024Day05().execute("src/test/resources/y2024/day05-input01.txt", isPart2 = true)
		assertEquals("6305", result)
	}
}