package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Question2024Day12Test {
    @Test
    fun testSample01() {
        val result = Question2024Day12().execute("src/test/resources/y2024/day12-sample01.txt")
        Assertions.assertEquals("1930", result)
    }

    @Test
    fun testSample02() {
        val result = Question2024Day12().execute("src/test/resources/y2024/day12-sample02.txt")
        Assertions.assertEquals("140", result)
    }

    @Test
    fun testSample03() {
        val result = Question2024Day12().execute("src/test/resources/y2024/day12-sample03.txt")
        Assertions.assertEquals("772", result)
    }

    @Test
    fun testInput01() {
        val result = Question2024Day12().execute("src/test/resources/y2024/day12-input01.txt")
        Assertions.assertEquals("1471452", result)
    }
}