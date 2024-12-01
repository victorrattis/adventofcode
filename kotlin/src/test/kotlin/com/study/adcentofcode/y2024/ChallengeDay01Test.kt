package com.study.adcentofcode.y2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ChallengeDay01Test {

    @Test
    fun test01() {
        val result = ChallengeDay01().execute("src/test/resources/y2024/question01-sample01.txt")
        assertEquals("11", result)
    }

    @Test
    fun testInput01() {
        val result = ChallengeDay01().execute("src/test/resources/y2024/challenge01-input.txt")
        assertEquals("1834060", result)
    }

    @Test
    fun testPart02() {
        val result = ChallengeDay01().execute2("src/test/resources/y2024/question01-sample01.txt")
        assertEquals("31", result)
    }

    @Test
    fun testInputPart02() {
        val result = ChallengeDay01().execute2("src/test/resources/y2024/challenge01-input.txt")
        assertEquals("0", result)
    }
}