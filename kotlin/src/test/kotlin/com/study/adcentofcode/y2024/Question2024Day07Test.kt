package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Question2024Day07Test {
	@Test
	fun test01() {
		val result = Question2024Day07().execute("src/test/resources/y2024/day07-sample01.txt")
		assertEquals("3749", result)
	}

	@Test
	fun testInput01() {
		val result = Question2024Day07().execute("src/test/resources/y2024/day07-input01.txt")
		assertEquals("267566105056", result)
	}

	@Test
	fun test01Part2() {
		val result = Question2024Day07().execute("src/test/resources/y2024/day07-sample01.txt", isPart2 = true)
		assertEquals("11387", result)
	}

	@Test
	fun testInput01Part2() {
		val result = Question2024Day07().execute("src/test/resources/y2024/day07-input01.txt", isPart2 = true)
		assertEquals("116094961956019", result)
	}
}