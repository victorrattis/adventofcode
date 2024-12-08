package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Question2024Day06Test {
	@Test
	fun test01() {
		val result = Question2024Day06().execute("src/test/resources/y2024/day06-sample01.txt")
		assertEquals("41", result)
	}

	@Test
	fun testInput() {
		val result = Question2024Day06().execute("src/test/resources/y2024/day06-input01.txt")
		assertEquals("0", result)
	}
}