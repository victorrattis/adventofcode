package com.study.adcentofcode.y2023

import com.study.adcentofcode.y2023.Question11
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question11Test {
    @Test
    fun checkSample01() {
        val result = Question11().execute(
            "src/test/resources/question11-sample01.txt"
        )
        assertEquals(374, result)
    }

    @Test
    fun checkPuzzle() {
        val result = Question11().execute(
            "src/test/resources/question11.txt"
        )
        assertEquals(9214785, result)
    }

    @Test
    fun checkSample01Part2() {
        val result = Question11().execute(
            "src/test/resources/question11-sample01.txt",
            100
        )
        assertEquals(8410, result)
    }

    @Test
    fun checkSample01Part2_2() {
        val result = Question11().execute(
            "src/test/resources/question11-sample01.txt",
            10
        )
        assertEquals(1030, result)
    }

    @Test
    fun checkPuzzlePart2() {
        val result = Question11().execute(
            "src/test/resources/question11.txt",
            1000000
        )
        assertEquals(613686987427, result)
    }
}