package com.study.adcentofcode

import com.study.adcentofcode.y2023.Question10
import com.study.adcentofcode.y2024.Question2024Day04
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main(args: Array<String>) {
    var result = ""
    val time = measureTime {
		result = Question2024Day04().execute("src/test/resources/y2024/day04-input01.txt", isPart2 = true)
    }
    println("time: $time")
    println(result)
}

