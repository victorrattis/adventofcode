package com.study.adcentofcode.y2024

class Question2024Day11: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		var stones: Map<Long, Long> = input.split(" ").map { it.trim().toLong() }.map { it to 1L }.toMap()
		val times = if (isPart2) 75 else 25
		for (i in 1 .. times) stones = calculateBlinking(stones)
		return stones.values.sum().toString()
	}

	private fun calculateBlinking(stones: Map<Long, Long>): Map<Long, Long> = mutableMapOf<Long, Long>().apply {
		for ((stone, count) in stones) {
			nextStoneValue(stone).forEach { this[it] = (this[it] ?: 0L) + count }
		}
	}

	private fun nextStoneValue(stone: Long): Array<Long> = if (stone == 0L) {
		arrayOf(1L)
	} else if ("$stone".length % 2 == 0) {
		"$stone".let {
			val index = it.length / 2
			arrayOf(it.take(index).toLong(), it.substring(index).toLong())
		}
	} else {
		arrayOf(stone * 2024L)
	}
}