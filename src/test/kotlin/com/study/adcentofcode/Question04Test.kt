package com.study.adcentofcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question04Test {
    @Test
    fun checkSample01() {
        val result = Question04().execute("src/test/resources/question04-sample01.txt")
        assertEquals(13, result)
    }
    @Test
    fun checkPuzzle() {
        val result = Question04().execute("src/test/resources/question04.txt")
        assertEquals(24706, result)
    }
    @Test
    fun checkSample01Part2() {
        val result = Question04().execute("src/test/resources/question04-sample01.txt",  isPart2 = true)
        assertEquals(30, result)
    }
    @Test
    fun checkPuzzlePart2() {
        val result = Question04().execute("src/test/resources/question04.txt", isPart2 = true)
        assertEquals(13114317, result)
    }
}