package com.study.adcentofcode.y2023

import com.study.adcentofcode.y2023.Question09
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question09Test {
    @Test
    fun checkSample01() {
        val result = Question09().execute(
            "src/test/resources/question09-sample01.txt"
        )
        assertEquals(114, result)
    }

    @Test
    fun checkPuzzle() {
        val result = Question09().execute(
            "src/test/resources/question09.txt"
        )
        assertEquals(2043183816, result)
    }

    @Test
    fun checkSample01Part2() {
        val result = Question09().execute(
            "src/test/resources/question09-sample02.txt",
            part2 = true
        )
        assertEquals(5, result)
    }

    @Test
    fun checkPuzzlePart2() {
        val result = Question09().execute(
            "src/test/resources/question09.txt",
            part2 = true
        )
        assertEquals(1118, result)
    }
}