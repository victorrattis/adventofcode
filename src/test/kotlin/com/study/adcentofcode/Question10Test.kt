package com.study.adcentofcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question10Test {
    @Test
    fun checkSample01() {
        val result = Question10().executeParte1(
            "src/test/resources/question10-sample01.txt"
        )
        assertEquals(4, result)
    }

    @Test
    fun checkSample02() {
        val result = Question10().executeParte1(
            "src/test/resources/question10-sample02.txt"
        )
        assertEquals(8, result)
    }

    @Test
    fun checkPuzzle() {
        val result = Question10().executeParte1(
            "src/test/resources/question10.txt"
        )
        assertEquals(6931, result)
    }

    @Test
    fun checkSample03Part2() {
        val result = Question10().executeParte2(
            "src/test/resources/question10-sample03.txt"
        )
        assertEquals(4, result)
    }

    @Test
    fun checkSample04Part2() {
        val result = Question10().executeParte2(
            "src/test/resources/question10-sample04.txt"
        )
        assertEquals(8, result)
    }

    @Test
    fun checkPuzzlePart2() {
        val result = Question10().executeParte2(
            "src/test/resources/question10.txt"
        )
        assertEquals(357, result)
    }

    @Test
    fun checkPuzzlePart2Optimized() {
        val result = Question10().executeParte2Optimized(
            "src/test/resources/question10.txt"
        )
        assertEquals(357, result)
    }
}