package com.study.adcentofcode.y2024

class Question2024Day06: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String = input
		.split(System.lineSeparator())
		.let { findSymbolsOnMap(it) }
		.let { calculateGuardMovementOnMap(it) }
		.toString()

	private fun calculateGuardMovementOnMap(puzzleInput: PuzzleInput): Int {
		val xFromY: MutableMap<Int, MutableList<Int>> = mutableMapOf()
		val yFromX: MutableMap<Int, MutableList<Int>> = mutableMapOf()

		puzzleInput.obstacles.forEach { coordinate ->
			if (!yFromX.containsKey(coordinate.y)) yFromX[coordinate.y] = mutableListOf()
			yFromX[coordinate.y]?.add(coordinate.x)
			if (!xFromY.containsKey(coordinate.x)) xFromY[coordinate.x] = mutableListOf()
			xFromY[coordinate.x]?.add(coordinate.y)
		}
		xFromY.values.forEach { it.sort() }
		yFromX.values.forEach { it.sort() }

		println(puzzleInput.guard)
		println(yFromX)
		println(xFromY)

		var isEnd = false
		 var distance = 0

		// Guard State: UP: 0, Right: 1, Down: 2, Left: 3
		xFromY[puzzleInput.guard.x].let {
			it?.find { y -> y < puzzleInput.guard.y }
				?.let { y -> puzzleInput.guard.y - (y + 1) }
				?: (puzzleInput.guard.y - 1).apply { isEnd = true;  }



			println(it)
		}

		return 0
	}

	private fun findSymbolsOnMap(map: List<String>): PuzzleInput {
		var guard: Coordinate? = null
		val obstacles: MutableList<Coordinate> = mutableListOf()
		for (y in map.indices) {
			for (x in 0 until map[y].length) {
				when (map[y][x]) {
					'#' -> { obstacles.add(Coordinate(x, y)) }
					'^' -> { guard = Coordinate(x, y) }
				}
			}
		}
		return guard?.let { PuzzleInput(it, obstacles) } ?: throw Exception("Guard Coordinate should not null!")
	}

	enum class Sense(val value: Int) {
		UP (0),
		RIGHT(1),
		DOWN(2),
		LEFT(3);
		fun flip(): Sense = fromInt((value + 1).let { if (it > 3) 0 else it })
		companion object {
			fun fromInt(value: Int) = Sense.values().first { it.value == value }
		}
	}

	private data class PuzzleInput(val guard: Coordinate, val obstacles: List<Coordinate>)
	private data class Coordinate(val x: Int, val y: Int)
}