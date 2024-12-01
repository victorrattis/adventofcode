package com.study.adcentofcode.y2023

import com.study.adcentofcode.y2023.Question07
import com.study.adcentofcode.y2023.Question07pt2
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question07Test {
    @Test
    fun checkSample01() {
        val result = Question07().execute("src/test/resources/question07-sample01.txt")
        assertEquals(6440, result)
    }

    @Test
    fun checkPuzzle() {
        val result = Question07().execute("src/test/resources/question07.txt")
        assertEquals(250602641, result)
    }

    @Test
    fun checkSample01pt2() {
        val result = Question07pt2().execute("src/test/resources/question07-sample01.txt")
        assertEquals(5905, result)
    }

    @Test
    fun checkPuzzlePt2() {
        val result = Question07pt2().execute("src/test/resources/question07.txt")
        assertEquals(251037509, result)
    }
}