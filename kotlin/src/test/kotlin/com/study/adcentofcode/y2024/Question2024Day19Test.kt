package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Question2024Day19Test {
	@Test
	fun testSample01() {
		val result = Question2024Day19().execute("src/test/resources/y2024/day19-sample01.txt")
		Assertions.assertEquals("6", result)
	}

	@Test
	fun testInput01() {
		val result = Question2024Day19().execute("src/test/resources/y2024/day19-input01.txt")
		Assertions.assertEquals("355", result)
	}

//	@Test
//	fun testSample01Part2() {
//		val result = Question2024Day19().execute("src/test/resources/y2024/day19-sample01.txt", isPart2 = true)
//		Assertions.assertEquals("16", result)
//	}
}