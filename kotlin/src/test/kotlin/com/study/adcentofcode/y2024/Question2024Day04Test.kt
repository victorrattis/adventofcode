package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Question2024Day04Test {
	@Test
	fun test01() {
		val result = Question2024Day04().execute("src/test/resources/y2024/day04-sample01.txt")
		assertEquals("18", result)
	}

	@Test
	fun test01Input01() {
		val result = Question2024Day04().execute("src/test/resources/y2024/day04-input01.txt")
		assertEquals("2401", result)
	}

	@Test
	fun test01Part2() {
		val result = Question2024Day04().execute("src/test/resources/y2024/day04-sample01.txt", isPart2 = true)
		assertEquals("9", result)
	}

	@Test
	fun test01Input01Part2() {
		val result = Question2024Day04().execute("src/test/resources/y2024/day04-input01.txt", isPart2 = true)
		assertEquals("1822", result)
	}
}