package com.study.adcentofcode.y2024

class Question2024Day16: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val maze = input.split(System.lineSeparator()).map { it.toCharArray() }
		fun isTileFree(coordinate: Coordinate): Boolean = maze[coordinate.y][coordinate.x] != '#'

		var startPoint: Coordinate = Coordinate.ZERO
		var endPoint: Coordinate = Coordinate.ZERO

		foreachMap(maze) { x, y, tile ->
			when(tile) {
				'S' -> startPoint = Coordinate(x, y)
				'E' -> endPoint = Coordinate(x, y)
			}
		}

		var nextPaths = mutableListOf<Path>()
		var paths: List<Path> = listOf(Path(startPoint, Coordinate.RIGHT, 0, if (isPart2) mutableListOf() else null))
		val scorePerCoordinate = mutableMapOf<Pair<Coordinate, Coordinate>, Int>().withDefault { Int.MAX_VALUE }

		var lowestScore = Int.MAX_VALUE
		val counts = mutableListOf<Pair<Int, List<Coordinate>>>()

		while (paths.isNotEmpty()) {
			for ((current, direction, score, visited) in paths) {
				if (current == endPoint) {
					if (lowestScore > score) lowestScore = score
					visited?.let { counts.add(score to visited) }
					continue
				}

				if (score > scorePerCoordinate.getValue(current to direction)) continue
				scorePerCoordinate[current to direction] = score

				if (isTileFree(current + direction)) {
					val nextVisited = visited?.toMutableList().also { it?.add(current) }
					nextPaths.add(Path(current + direction, direction, score + 1, nextVisited))
				}

				for (nexDirection in Coordinate.moves[direction]!!) {
					val next = current + nexDirection
					if (isTileFree(next)) {
						val nextVisited = visited?.toMutableList().also { it?.add(current) }
						nextPaths.add(Path(next, nexDirection, score + 1001, nextVisited))
					}
				}
			}

			paths = nextPaths
			nextPaths = mutableListOf()
		}

		return if (isPart2 ) {
			counts.filter { it.first == lowestScore }.flatMap { it.second }.distinct().count().let { it + 1 }.toString()
		} else {
			lowestScore.toString()
		}
	}

	private data class Path(
		var current: Coordinate,
		var direction: Coordinate,
		var score: Int,
		var visited: List<Coordinate>?
	)

	private fun foreachMap(map: List<CharArray>, callback: (x: Int, y: Int, tile: Char) -> Unit) {
		map.forEachIndexed { y, line ->
			line.forEachIndexed { x, tile ->
				callback(x, y, tile)
			}
		}
	}

	private data class Coordinate(val x: Int, val y: Int) {
		operator fun plus(value: Coordinate): Coordinate = this.copy(x = x + value.x, y = y + value.y)
		operator fun minus(value: Coordinate): Coordinate = this.copy(x = x - value.x, y = y - value.y)

		companion object {
			val UP = Coordinate(0, -1)
			val DOWN = Coordinate(0, 1)
			val RIGHT = Coordinate(1, 0)
			val LEFT = Coordinate(-1, 0)
			val ZERO = Coordinate(0, 0)

			val moves = mutableMapOf(
				UP to listOf(RIGHT, LEFT),
				DOWN to listOf(RIGHT, LEFT),
				RIGHT to listOf(UP, DOWN),
				LEFT to listOf(UP, DOWN)
			)
		}

		override fun toString(): String {
			return "($x, $y)"
		}
	}
}