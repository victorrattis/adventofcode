package com.study.adcentofcode.y2023

import com.study.adcentofcode.y2023.Question03
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Question03Test {
    @Test
    fun checkSample01() {
        val result = Question03().execute("src/test/resources/question03-sample01.txt")
        assertEquals("4361", result)
    }

    @Test
    fun checkPuzzle() {
        val result = Question03().execute("src/test/resources/question03.txt")
        assertEquals("539590", result)
    }

    @Test
    fun checkSample01pt2() {
        val result = Question03().executePt2("src/test/resources/question03-sample01.txt")
        assertEquals("467835", result)
    }

    @Test
    fun checkPuzzlePt2() {
        val result = Question03().executePt2("src/test/resources/question03.txt")
        assertEquals("80703636", result)
    }
}