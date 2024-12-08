package com.study.adcentofcode.y2024

class Question2024Day05: Question() {
	data class InputDetail(val rules: Map<Int, Set<Int>>, val pages: List<List<Int>>)

	override fun executeInput(input: String, isPart2: Boolean): String = loadInputDetail(input).let { detail ->
		detail.pages
			.filter { isPagesCorrectOrder(detail.rules, it).let { isOrdered -> if(isPart2) !isOrdered else isOrdered } }
			.map { if (isPart2) orderPages(detail.rules, it) else it }
			.sumOf { it.middle() }.toString()
	}

	private fun loadInputDetail(input: String): InputDetail {
		val lines = input.split(System.lineSeparator())
		var index = 0
		var line: String

		val rules: MutableMap<Int, MutableSet<Int>> = mutableMapOf()

		line = lines[index]
		while (line.isNotBlank()) {
			line.split("|").map { it.toInt() }.let {
				if (!rules.containsKey(it[0])) {
					rules[it[0]] = mutableSetOf()
				}
				rules[it[0]]?.add(it[1])
			}
			line = lines[++index]
		}

		index++
		val pages: MutableList<List<Int>> = mutableListOf()
		for (i in index until lines.size) {
			pages.add(lines[i].split(",").map { it.toInt() }.toList())
		}

		return InputDetail(rules, pages)
	}

	private fun isPagesCorrectOrder(rules: Map<Int, Set<Int>>, pages: List<Int>): Boolean {
		for (i in 0 until (pages.size - 1)) {
			if(rules[pages[i]]?.let { allRulesCorrect(it, pages, i) } != true) return false
		}
		return true
	}

	private fun allRulesCorrect(rules: Set<Int>, pages: List<Int>, index: Int): Boolean {
		for (i in index + 1 until pages.size) {
			if (!rules.contains(pages[i])) return false
		}
		return true
	}

	private fun orderPages(rules: Map<Int, Set<Int>>, pages: List<Int>): List<Int> = pages.sortedWith { first, second ->
		if (rules[first]?.contains(second) == true) +1
		else if (rules[second]?.contains(first) == true) - 1
		else 0
	}

	private fun List<Int>.middle() = get(size / 2)
}