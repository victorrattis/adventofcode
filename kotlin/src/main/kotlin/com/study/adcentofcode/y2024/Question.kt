package com.study.adcentofcode.y2024

import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

abstract class Question {
	fun execute(filePath: String, isPart2: Boolean = false): String =
		executeInput(getFileContent(filePath), isPart2)

	protected abstract fun executeInput(input: String, isPart2: Boolean): String

	fun measureExecution(filePath: String, isPart2: Boolean): String {
		val input = getFileContent(filePath)
		return measureExecution {
			executeInput(input, isPart2)
		}
	}

	private fun getFileContent(filePath: String): String =
		File(filePath).inputStream().bufferedReader().readText()

	@OptIn(ExperimentalTime::class)
	private fun measureExecution(execute: () -> String): String {
		var result: String
		return measureTime {
			result = execute()
		}.let {
			"{ time: $it, result: $result }"
		}
	}
}
