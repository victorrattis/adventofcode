package com.study.adcentofcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question02Test {
    @Test
    fun checkSample01() {
        val result = Question02().execute("src/test/resources/question02-sample01.txt")
        assertEquals("8", result)
    }

    @Test
    fun checkPuzzle() {
        val result = Question02().execute("src/test/resources/question02.txt")
        assertEquals("2528", result)
    }

    @Test
    fun checkSample02() {
        val result = Question02pt2().execute("src/test/resources/question02-sample02.txt")
        assertEquals("2286", result)
    }

    @Test
    fun checkPuzzlePt2() {
        val result = Question02pt2().execute("src/test/resources/question02.txt")
        assertEquals("67363", result)
    }
}