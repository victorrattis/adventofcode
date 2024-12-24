package com.study.adcentofcode.y2024

class Question2024Day19: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val puzzle = loadPuzzleData(input)

		val patterns = HashMap<Char, MutableList<String>>()
		puzzle.patterns.forEach {
			if (it[0] !in patterns) patterns[it[0]] = mutableListOf()
			patterns[it[0]]?.add(it)
		}

		return puzzle.designs.count { checkRegex(it, puzzle.patterns) }.toString()
	}

	private fun checkRegex(design: String, patterns: List<String>): Boolean {
		return Regex("(${patterns.joinToString("|")})+").matches(design)
	}

	private fun loadPuzzleData(input: String): Puzzle {
		val iterator = input.split(System.lineSeparator()).iterator()
		val patterns = iterator.next().split(",").map { it.trim() }
		iterator.next()

		val desiredDesigns = mutableListOf<String>()
		while (iterator.hasNext()) {
			desiredDesigns.add(iterator.next())
		}
		return Puzzle(patterns, desiredDesigns)
	}

	private data class Puzzle(val patterns: List<String>, val designs: List<String>)
}