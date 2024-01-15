package com.study.adcentofcode

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
}