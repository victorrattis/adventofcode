package com.study.adcentofcode.y2024

import java.io.File
import kotlin.streams.toList

@OptIn(ExperimentalStdlibApi::class)
class Question2024Day04 {
	private val operations = listOf(
		Pair(1, 0), Pair(-1, 0), // horizontal
		Pair(0, -1), Pair(0, 1), // vertical
		Pair(1, 1), Pair(1, -1),
		Pair(-1, 1), Pair(-1, -1)
	)

	fun execute(filePath: String, isPart2: Boolean = false): String = File(filePath).inputStream().bufferedReader()
		.lines().toList()
		.let { findWordsOnText(it, "XMAS") }
		.toString()

	private fun findWordsOnText(text: List<String>, word: String): Int {
		var sum = 0
		for (y in text.indices) {
			for (x in 0..< text[y].length) {
				if (text[y][x] == word[0]) sum += checkWord(text, word, x, y)
			}
		}
		return sum
	}

	private fun checkWord(text: List<String>, word: String, x: Int, y: Int): Int = operations.sumOf { operation ->
		var tempX = x
		var tempY = y
		word.all { character ->
			(character == getItem(text, tempX, tempY)).apply {
				tempX += 1 * operation.second
				tempY += 1 * operation.first
			}
		}.let { if(it) 1 else 0 }.toInt()
	}

	private fun getItem(text: List<String>, x: Int, y: Int): Char? =
		if (y !in text.indices || x !in 0 until text[y].length) null else text[y][x]
}