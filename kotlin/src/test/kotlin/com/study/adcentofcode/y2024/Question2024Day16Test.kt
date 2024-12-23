package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Question2024Day16Test {
	@Test
	fun testSample01() {
		val result = Question2024Day16().execute("src/test/resources/y2024/day16-sample01.txt")
		Assertions.assertEquals("7036", result)
	}

	@Test
	fun testSample02() {
		val result = Question2024Day16().execute("src/test/resources/y2024/day16-sample02.txt")
		Assertions.assertEquals("11048", result)
	}

	@Test
	fun testInput01() {
		val result = Question2024Day16().execute("src/test/resources/y2024/day16-input01.txt")
		Assertions.assertEquals("85396", result)
	}

	@Test
	fun testSample01Part2() {
		val result = Question2024Day16().execute("src/test/resources/y2024/day16-sample01.txt", isPart2 = true)
		Assertions.assertEquals("45", result)
	}

	@Test
	fun testSample02Part2() {
		val result = Question2024Day16().execute("src/test/resources/y2024/day16-sample02.txt", isPart2 = true)
		Assertions.assertEquals("64", result)
	}

	@Test
	fun testInput01Part2() {
		val result = Question2024Day16().execute("src/test/resources/y2024/day16-input01.txt", isPart2 = true)
		Assertions.assertEquals("428", result)
	}
}