package com.study.adcentofcode

import java.io.File

class Question11 {
    fun executeParte1(filePath: String): Long {
        loadDataFromFile(filePath)
        return 0L
    }

    private fun loadDataFromFile(filePath: String) {
        File(filePath).inputStream().bufferedReader().forEachLine { line ->
        }
    }
}