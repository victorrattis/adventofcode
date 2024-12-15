package com.study.adcentofcode.y2024

class Question2024Day10: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val map = input.split(System.lineSeparator()).map { it.map { it.digitToInt() }.toIntArray() }
		val startPoints = findStartPoints(map)

		var sum = 0
		for (i in startPoints.indices) {
			val result = mutableMapOf<Coordinate, Int>()
			checkTrailheads(map, startPoints[i], result, 0)
			sum += if (isPart2) result.entries.sumOf { it.value } else result.size
		}
		return sum.toString()
	}

	private fun checkTrailheads(map: List<IntArray>, current: Coordinate, results: MutableMap<Coordinate, Int>, n: Int) {
		try {
			if (map[current.x][current.y] != n) return
			if (map[current.x][current.y] == 9) {
				results[current] = (results[current] ?: 0) + 1
				return
			}
		} catch (e: IndexOutOfBoundsException) {
			return
		}

		val nextElevation = n + 1
		checkTrailheads(map, current + Coordinate.RIGHT, results, nextElevation)
		checkTrailheads(map, current + Coordinate.LEFT, results, nextElevation)
		checkTrailheads(map, current + Coordinate.UP, results, nextElevation)
		checkTrailheads(map, current + Coordinate.DOWN, results, nextElevation)
	}

	private fun findStartPoints(map: List<IntArray>): Array<Coordinate> {
		val startPoints = mutableListOf<Coordinate>()
		map.forEachIndexed { x, lines ->
			lines.forEachIndexed { y, c ->
				if (c == 0) startPoints.add(Coordinate(x, y))
			}
		}
		return startPoints.toTypedArray()
	}

	private data class Coordinate(val x: Int, val y: Int) {
		operator fun plus(value: Coordinate): Coordinate = this.copy(x = x + value.x, y = y + value.y)
		companion object {
			val UP = Coordinate(0, -1)
			val DOWN = Coordinate(0, 1)
			val RIGHT = Coordinate(1, 0)
			val LEFT = Coordinate(-1, 0)
		}
	}
}