package com.study.adcentofcode.y2024

class Question2024Day19: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val puzzle = loadPuzzleData(input)

//		return puzzle.designs.count { checkRegex(it, puzzle.patterns) }.toString()

		val patterns = HashMap<Char, MutableList<String>>()
		puzzle.patterns.forEach {
			if (it[0] !in patterns) patterns[it[0]] = mutableListOf()
			patterns[it[0]]?.add(it)
		}
		return puzzle.designs.count { isPossibleDesign(it, patterns) }.toString()
	}

	private fun isPossibleDesign(design: String, patterns: HashMap<Char, MutableList<String>>): Boolean {
		var i = 0

		do {
			val result = patterns[design[i]]
			if (result?.isEmpty() != false) return false

			val found = findAllPatternAt(design, result, i) ?: return false
			i += found.length
		} while (i < design.length)

		return true
	}

	private fun findAllPatternAt(design: String, patterns: List<String>, index: Int): String? {
		var pattern: String? = null

		patterns.forEach {
			try {
				var all = true
				for (i in it.indices) {
					all = all && (design[index + i] == it[i])
				}
				if (all) {
					if (pattern == null || pattern!!.length < it.length) {
						pattern = it
					}
				}
			} catch (_: StringIndexOutOfBoundsException) {
			}
		}

		return pattern
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