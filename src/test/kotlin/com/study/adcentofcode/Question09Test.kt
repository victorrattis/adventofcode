package com.study.adcentofcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question09Test {
    @Test
    fun checkSample01() {
        val result = Question09().execute("src/test/resources/question09-sample01.txt")
        assertEquals(114, result)
    }

    @Test
    fun checkPuzzle() {
        val result = Question09().execute("src/test/resources/question09.txt")
        assertEquals(2043183816, result)
    }
}