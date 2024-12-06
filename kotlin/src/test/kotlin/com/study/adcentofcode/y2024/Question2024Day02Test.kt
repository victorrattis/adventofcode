package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question2024Day02Test {
    @Test
    fun test01() {
        val result = Question2024Day02().execute("src/test/resources/y2024/day02-sample01.txt")
        assertEquals("2", result)
    }

    @Test
    fun testInput01() {
        val result = Question2024Day02().execute("src/test/resources/y2024/day02-input01.txt")
        assertEquals("483", result)
    }

    @Test
    fun test01Parte2() {
        val result = Question2024Day02().execute("src/test/resources/y2024/day02-sample01.txt", isPart2 = true)
        assertEquals("4", result)
    }

    @Test
    fun test02Parte2() {
        val result = Question2024Day02().execute("src/test/resources/y2024/day02-sample02.txt", isPart2 = true)
        assertEquals("6", result)
    }

    @Test
    fun testPart2Input01() {
        val result = Question2024Day02().execute("src/test/resources/y2024/day02-input01.txt", isPart2 = true)
        assertEquals("528", result)
    }
}