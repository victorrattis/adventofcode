package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Question2024Day17Test {
	@Test
	fun testSample01() {
		val result = Question2024Day17().execute("src/test/resources/y2024/day17-sample01.txt")
		Assertions.assertEquals("4,6,3,5,6,3,5,2,1,0", result)
	}

	@Test
	fun testInput1() {
		val result = Question2024Day17().execute("src/test/resources/y2024/day17-input01.txt")
		Assertions.assertEquals("3,5,0,1,5,1,5,1,0", result)
	}

	@Test
	fun testSample02Part2() {
		val result = Question2024Day17().execute("src/test/resources/y2024/day17-sample02.txt", isPart2 = true)
		Assertions.assertEquals("117440", result)
	}

	@Test
	fun testInput1Part2() {
		val result = Question2024Day17().execute("src/test/resources/y2024/day17-input01.txt", isPart2 = true)
		Assertions.assertEquals("107413700225434", result)
	}
}