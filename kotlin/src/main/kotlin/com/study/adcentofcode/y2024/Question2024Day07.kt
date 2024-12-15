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

	private fun calculateOperation(numbers: List<Long>, operators: Array<OperatorType>): Long =
		numbers.reduceIndexed { index, acc, i ->
			when(operators[index - 1]) {
				OperatorType.ADD -> { acc + i }
				OperatorType.MULTIPLY -> { acc * i }
				OperatorType.CONCATENATION -> { "$acc$i".toLong() }
			}
		}

	private inline fun <reified T> permutationsFromList(values: List<T>, n: Int): List<Array<T>> {
		val array = Array(n) { values[0] }
		val results: MutableList<Array<T>> = mutableListOf()
		permutationCycle(values, n, array, results, 0)
		return results
	}

	private fun <T> permutationCycle(values: List<T>, n: Int, array: Array<T>, results: MutableList<Array<T>>, depth: Int) {
		if (depth == array.size) {
			results.add(array.copyOf())
			return
		}
		for (element in values) {
			array[depth] = element
			permutationCycle(values, n, array, results, depth + 1)
		}
	}

	enum class OperatorType {
		ADD,
		MULTIPLY,
		CONCATENATION
	}
}

