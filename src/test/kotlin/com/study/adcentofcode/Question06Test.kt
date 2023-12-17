package com.study.adcentofcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.time.measureTime

class Question06Test {
    @Test
    fun checkSample01() {
        val result = Question06().execute("src/test/resources/question06-sample01.txt")
        assertEquals(288, result)
    }

    @Test
    fun checkPuzzle() {
        val result = Question06().execute("src/test/resources/question06.txt")
        assertEquals(3317888, result)
    }

    @Test
    fun checkSample01Part2() {
        val result = Question06().execute("src/test/resources/question06-sample01.txt", isPart2 = true)
        assertEquals(71503, result)
    }

    @Test
    fun checkPuzzlePart2() {
        val result = Question06().execute("src/test/resources/question06.txt", isPart2 = true)
        assertEquals(24655068, result)
    }

}