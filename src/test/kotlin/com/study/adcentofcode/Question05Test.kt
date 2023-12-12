package com.study.adcentofcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question05Test {
    @Test
    fun checkSample01() {
        val result = Question05().execute("src/test/resources/question05-sample01.txt")
        assertEquals(35L, result)
    }

    @Test
    fun checkPuzzle() {
        val result = Question05().execute("src/test/resources/question05.txt")
        assertEquals(261668924L, result)
    }

    @Test
    fun checkSample01Pt2() {
        val result = Question05().execute("src/test/resources/question05-sample01.txt", isPart2 = true)
        assertEquals(46L, result)
    }
//
//    @Test
//    fun checkPuzzlePart2() {
//        val result = Question05().execute("src/test/resources/question05.txt", isPart2 = true)
//        assertEquals(0L, result)
//    }
}