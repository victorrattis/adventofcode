package com.study.adcentofcode.y2023

import com.study.adcentofcode.y2023.Question12
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question12Test {
    @Test
    fun checkSample01() {
        val result = Question12().execute(
            "src/test/resources/question12-sample01.txt"
        )
        assertEquals(21, result)
    }

    @Test
    fun checkPuzzle() {
        val result = Question12().execute(
            "src/test/resources/question12.txt"
        )
        assertEquals(7204, result)
    }

    @Test
    fun checkSample01Part2() {
        val result = Question12().execute(
            "src/test/resources/question12-sample01.txt",
            5
        )
        assertEquals(525152, result)
    }

    @Test
    fun checkPuzzlePart2() {
        val result = Question12().execute(
            "src/test/resources/question12.txt",
            5
        )
        assertEquals(1672318386674, result)
    }
}