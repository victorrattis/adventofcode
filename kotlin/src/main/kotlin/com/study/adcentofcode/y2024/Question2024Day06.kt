package com.study.adcentofcode.y2024

class Question2024Day06: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String = input
		.split(System.lineSeparator()).map { it.toCharArray() }
		.let { if(isPart2) processGuardMovementWithObstruction(it) else processGuardMovement(it) }
		.toString()

	private fun processGuardMovement(map: List<CharArray>): Int {
		var guardSense = Sense.UP
		var guardPosition = findSymbolsOnMap(map)
		val lines = map.size
		val columns = map[0].size
		var newPosition: Coordinate = guardPosition

		do {
			val title = map[newPosition.y][newPosition.x]
			if (title == '#') {
				guardSense = guardSense.flip()
			} else {
				map[newPosition.y][newPosition.x] = 'X'
				guardPosition = newPosition
			}
			newPosition = guardPosition + guardSense.movement
		} while (newPosition.x in 0 until columns && newPosition.y in 0 until lines)

		return map.sumOf { it.count { tile -> tile == 'X' } }
	}

	private fun processGuardMovementWithObstruction(map: List<CharArray>): Int {
		var guardSense = Sense.UP
		val initialPosition = findSymbolsOnMap(map)
		var guardPosition = initialPosition
		val lines = map.size
		val columns = map[0].size
	  	var newPosition: Coordinate = guardPosition

		// contain all the coordinates X in column Y.
		val xFromY: MutableMap<Int, MutableList<Int>> = mutableMapOf()
		// contain all the coordinates Y in line X.
		val yFromX: MutableMap<Int, MutableList<Int>> = mutableMapOf()

		for (y in 0 until lines) {
			for (x in 0 until columns) {
				when (map[y][x]) {
					'#' -> {
						if (!xFromY.containsKey(y)) xFromY[y] = mutableListOf()
						xFromY[y]?.add(x)
						if (!yFromX.containsKey(x)) yFromX[x] = mutableListOf()
						yFromX[x]?.add(y)
					}
				}
			}
		}
		var count = 0
		var nextSense = guardSense.flip()
		do {
			val title = map[newPosition.y][newPosition.x]
			if (title == '#') {
				guardSense = guardSense.flip()
				nextSense = guardSense.flip()
			} else {
				if (nextSense == Sense.RIGHT) {
					val isLooped = xFromY[newPosition.y]?.find { x -> newPosition.x < x }
						?.let { Coordinate(it, newPosition.y) }
						?.let { obstruction ->
							if (obstruction + Sense.LEFT.movement != newPosition)
							checkLoop(map, lines, obstruction + Sense.LEFT.movement, nextSense.flip(), newPosition, guardSense)
							else null
						}
					if (isLooped == true) count++
				} else if (nextSense == Sense.DOWN) {
					val isLooped = yFromX[newPosition.x]?.find {y -> newPosition.y < y }
						?.let { Coordinate(newPosition.x, it) }
						?.let { obstruction ->
							if (obstruction + Sense.UP.movement != newPosition)
							checkLoop(map, lines, obstruction + Sense.UP.movement, nextSense.flip(), newPosition, guardSense)
							else null
						}
					if (isLooped == true) count++
				} else if (nextSense == Sense.LEFT) {
					val isLooped = xFromY[newPosition.y]?.find { x -> newPosition.x >x }
						?.let { Coordinate(it, newPosition.y) }
						?.let { obstruction ->
							if (obstruction + Sense.RIGHT.movement != newPosition)
							checkLoop(map, lines, obstruction + Sense.RIGHT.movement, nextSense.flip(), newPosition, guardSense)
							else null
						}
					if (isLooped == true) count++
				} else if (nextSense == Sense.UP) {
					val isLooped = yFromX[newPosition.x]?.find {y -> newPosition.y > y }
						?.let { Coordinate(newPosition.x, it) }
						?.let { obstruction ->
							if (obstruction + Sense.DOWN.movement != newPosition)
							checkLoop(map, lines, obstruction + Sense.DOWN.movement, nextSense.flip(), newPosition, guardSense)
							else null
						}

					if (isLooped == true) count++
				}

				guardPosition = newPosition
			}
			newPosition = guardPosition + guardSense.movement
		} while (newPosition.x in 0 until lines && newPosition.y in 0 until columns)

		return count //map.sumOf { it.count { tile -> tile == 'X' } }
	}

	private fun checkLoop(map: List<CharArray>, size: Int, guardPosition: Coordinate, guardSense: Sense, loop: Coordinate, loopSense: Sense): Boolean {
		var newPosition: Coordinate = guardPosition
		var current = guardPosition
		var currentSense = guardSense
		var histories: MutableSet<Cache> = mutableSetOf()

		do {
			val title = map[newPosition.y][newPosition.x]
			if (title == '#') {
				if (histories.contains(Cache(newPosition, currentSense))) {
					return true
				}
				histories.add(Cache(newPosition, currentSense))
				currentSense = currentSense.flip()
			} else {
				current = newPosition
			}
			newPosition = current + currentSense.movement

			if (current == loop && currentSense == loopSense) return true
		} while (newPosition.x in 0 until size && newPosition.y in 0 until size)

		return false
	}

	private fun findSymbolsOnMap(map: List<CharArray>): Coordinate {
		var guard: Coordinate? = null
		for (y in map.indices) {
			for (x in 0 until map[y].size) {
				when (map[y][x]) {
					'^' -> { guard = Coordinate(x, y) }
				}
			}
		}

		return guard ?: throw Exception("Guard Coordinate should not null!")
	}
	enum class Sense(val value: Int, val movement: Coordinate) {
		UP(0,    Coordinate(0, -1)),
		RIGHT(1, Coordinate(1, 0)),
		DOWN(2,  Coordinate(0, 1)),
		LEFT(3,  Coordinate(-1, 0));
		fun flip(): Sense = fromInt((value + 1).let { if (it > 3) 0 else it })
		companion object {
			fun fromInt(value: Int) = Sense.values().first { it.value == value }
		}
	}

	data class Cache(val coord: Coordinate, val sense: Sense)

	data class Coordinate(var x: Int, var y: Int) {
		operator fun plus(increment: Coordinate): Coordinate = Coordinate(
			x + increment.x, y + increment.y
		)
	}
}