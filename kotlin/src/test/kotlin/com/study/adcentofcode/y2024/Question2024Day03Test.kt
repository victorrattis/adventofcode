package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question2024Day03Test {
     @Test
     fun test01() {
        val result = Question2024Day03().execute("src/test/resources/y2024/day03-sample01.txt")
        assertEquals("161", result)
     }

    @Test
    fun testInput01() {
        val result = Question2024Day03().execute("src/test/resources/y2024/day03-input01.txt")
        assertEquals("165225049", result)
    }

    @Test
    fun test01Part2() {
        val result = Question2024Day03().execute("src/test/resources/y2024/day03-sample02.txt", isPart2 = true)
        assertEquals("48", result)
    }

    @Test
    fun testInput01Part2() {
        val result = Question2024Day03().execute("src/test/resources/y2024/day03-input01.txt", isPart2 = true)
        assertEquals("108830766", result)
    }
}