package com.study.adcentofcode

import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main(args: Array<String>) {
//    println("Program arguments: ${args.joinToString()}")

//    val result = Question05().execute("src/test/resources/question05-sample01.txt", isPart2 = true)
//    println("Processing Question 08 part 2")
    var result = 0L
    val time = measureTime {
//        result = Question05().execute("src/test/resources/question05-sample01.txt", isPart2 = true)
//        result = Question05().execute("src/test/resources/question05.txt", isPart2 = true)
//        result = Question08().execute("src/test/resources/question08.txt", part2 = true)
        val question = Question10()
        result = question.executeParte2Optimized(
            "src/test/resources/question10.txt"
        )
    }
    println("time: $time")
    println(result)
}

