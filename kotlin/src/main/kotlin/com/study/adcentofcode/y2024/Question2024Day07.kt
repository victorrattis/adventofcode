package com.study.adcentofcode.y2024

class Question2024Day07: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		return input.split(System.lineSeparator())
			.map { it.split(": ") }
			.map { it[0].toLong() to it[1].trim().split(" ").map { number -> number.toLong() } }
			.sumOf { if (isEvaluatedLeftToRight(it.first, it.second, isPart2)) { it.first } else 0 }
			.toString()
	}

	private fun isEvaluatedLeftToRight(expected: Long, numbers: List<Long>, isPart2: Boolean): Boolean {
		val types = if (isPart2) {
			listOf(OperatorType.ADD, OperatorType.MULTIPLY, OperatorType.CONCATENATION)
		} else {
			listOf(OperatorType.ADD, OperatorType.MULTIPLY)
		}
		val operators = permutationsFromList(types, numbers.size - 1)
		return operators.any { expected == calculateOperation(numbers, it) }
	}

	private fun calculateOperation(numbers: List<Long>, operators: List<OperatorType>): Long =
		numbers.reduceIndexed { index, acc, i ->
			when(operators[index - 1]) {
				OperatorType.ADD -> { acc + i }
				OperatorType.MULTIPLY -> { acc * i }
				OperatorType.CONCATENATION -> { "$acc$i".toLong() }
			}
		}

	private fun permutationsFromList(values: List<OperatorType>, n: Int): List<List<OperatorType>> {
		if (n == 0) return listOf(emptyList())
		val result = mutableListOf<List<OperatorType>>()

		for (value in values) {
			for (subPermutation in permutationsFromList(values, n - 1)) {
				result.add(listOf(value) + subPermutation)
			}
		}
		return result
	}

	enum class OperatorType {
		ADD,
		MULTIPLY,
		CONCATENATION
	}
}

