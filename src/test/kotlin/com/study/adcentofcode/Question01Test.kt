package com.study.adcentofcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question01Test {
    @Test
    fun checkSample01() {
        val result = Question01().execute("src/test/resources/question01-sample01.txt")
        assertEquals("142", result)
    }

    @Test
    fun checkSample02() {
        val result = Question01().execute("src/test/resources/question01-sample02.txt")
        assertEquals("281", result)
    }


    @Test
    fun checkPuzzle() {
        val result = Question01().execute("src/test/resources/question01.txt")
        assertEquals("54770", result)
    }
}