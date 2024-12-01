package com.study.adcentofcode.y2023

import com.study.adcentofcode.y2023.Question13
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question13Test {
    @Test
    fun checkSample01() {
        val result = Question13().execute(
            "src/test/resources/question13-sample01.txt"
        )
        assertEquals(405, result)
    }

    @Test
    fun checkPuzzle() {
        val result = Question13().execute(
            "src/test/resources/question13.txt"
        )
        assertEquals(40006, result)
    }

    @Test
    fun checkSample01Part2() {
        val result = Question13().execute(
            "src/test/resources/question13-sample01.txt",
            part2 = true
        )
        assertEquals(400, result)
    }

    @Test
    fun checkPuzzlePart2() {
        val result = Question13().execute(
            "src/test/resources/question13.txt",
            part2 = true
        )
        assertEquals(28627, result)
    }
}