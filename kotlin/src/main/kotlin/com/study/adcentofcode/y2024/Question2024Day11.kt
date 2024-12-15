package com.study.adcentofcode.y2024

class Question2024Day11: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val stones = input.split(" ").map { it.trim().toLong() }.toLongArray()

		val times = if (isPart2) 75 else 25
		var newStones = stones
		for (i in 1.. times) {
			newStones = calculateBlinking(newStones)
		}
		return newStones.size.toString()
	}

	private fun calculateBlinking(stones: LongArray): LongArray {
		val result = mutableListOf<Long>()

		stones.forEachIndexed { index, stone ->
			if (stone == 0L) {
				result.add(1L)
			} else if ("$stone".length % 2 == 0) {
				val word = "$stone"
				val numbers = word.splitAtIndex(word.length / 2)
				result.add(numbers.first.toLong())
				result.add(numbers.second.toLong())
			} else {
				result.add(stone * 2024L)
			}
		}

		return result.toLongArray()
	}

	private fun String.splitAtIndex(index : Int) = take(index) to substring(index)
}