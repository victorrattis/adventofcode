package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question2024Day01Test {

    @Test
    fun test01() {
        val result = Question2024Day01().execute("src/test/resources/y2024/day01-sample01.txt")
        assertEquals("11", result)
    }

    @Test
    fun testInput01() {
        val result = Question2024Day01().execute("src/test/resources/y2024/day01-input.txt")
        assertEquals("1834060", result)
    }

    @Test
    fun testPart02() {
        val result = Question2024Day01().execute2("src/test/resources/y2024/day01-sample01.txt")
        assertEquals("31", result)
    }

    @Test
    fun testInputPart02() {
        val result = Question2024Day01().execute2("src/test/resources/y2024/day01-input.txt")
        assertEquals("21607792", result)
    }
}