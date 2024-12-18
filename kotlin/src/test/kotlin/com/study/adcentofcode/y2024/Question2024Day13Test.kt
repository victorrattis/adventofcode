package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Question2024Day13Test {
	@Test
	fun testSample01() {
		val result = Question2024Day13().execute("src/test/resources/y2024/day13-sample01.txt")
		Assertions.assertEquals("480", result)
	}

	@Test
	fun testInput01() {
		val result = Question2024Day13().execute("src/test/resources/y2024/day13-input01.txt")
		Assertions.assertEquals("35729", result)
	}


	@Test
	fun testSample01Part2() {
		val result = Question2024Day13().execute("src/test/resources/y2024/day13-sample01.txt", isPart2 = true)
		Assertions.assertEquals("875318608908", result)
	}

	@Test
	fun testInput01Part2() {
		val result = Question2024Day13().execute("src/test/resources/y2024/day13-input01.txt", isPart2 = true)
		Assertions.assertEquals("88584689879723", result)
	}
}