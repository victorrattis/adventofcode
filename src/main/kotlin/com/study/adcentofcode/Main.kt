package com.study.adcentofcode

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main(args: Array<String>) = runBlocking {
//    println("Program arguments: ${args.joinToString()}")

//    val result = Question05().execute("src/test/resources/question05-sample01.txt", isPart2 = true)
    println("Processing Question 05 part 2")
    var result = 0L
    val time = measureTime {
        result = Question05().execute("src/test/resources/question05-sample01.txt", isPart2 = true)
//        result = Question05().execute("src/test/resources/question05.txt", isPart2 = true)
    }
    println("time: $time")
    println(result)

}

