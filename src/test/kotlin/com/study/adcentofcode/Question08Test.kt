package com.study.adcentofcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question08Test {
    @Test
    fun checkSample01() {
        val result = Question08().execute("src/test/resources/question08-sample01.txt")
        assertEquals(2, result)
    }

    @Test
    fun checkSample02() {
        val result = Question08().execute("src/test/resources/question08-sample02.txt")
        assertEquals(6, result)
    }

    @Test
    fun checkPuzzle() {
        val result = Question08().execute("src/test/resources/question08.txt")
        assertEquals(21797, result)
    }

    @Test
    fun checkSample01Pt2() {
        val result = Question08().execute("src/test/resources/question08-sample03.txt", part2 = true)
        assertEquals(6L, result)
    }


    @Test
    fun checkPuzzlePt2() {
        val result = Question08().execute("src/test/resources/question08.txt", part2 = true)
        assertEquals(23977527174353L, result)
    }
}