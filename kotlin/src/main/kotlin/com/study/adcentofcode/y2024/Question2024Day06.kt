package com.study.adcentofcode.y2024

class Question2024Day06: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String = input
		.split(System.lineSeparator()).map { it.toCharArray() }
		.let { processGuardMovement(Grid(it), isPart2) }
		.toString()

	private fun processGuardMovement(map: Grid, isPart2: Boolean): Int {
		val initialPosition = findSymbolsOnMap(map)
		val guardRoute = processGuardRoute(map, initialPosition)
		return if(isPart2) { guardRoute.count { checkLoop(map, it, initialPosition) } } else guardRoute.size
	}

	private fun processGuardRoute(map: Grid, initialPosition: Coordinate): Set<Coordinate> {
		var guardSense = Direction.UP
		var guardPosition = initialPosition
		var newPosition = guardPosition
		val route = mutableSetOf<Coordinate>()

		do {
			if (map[newPosition.y][newPosition.x] == '#') {
				guardSense = guardSense.flip()
			} else {
				route.add(newPosition)
				guardPosition = newPosition
			}
			newPosition = guardPosition + guardSense.movement
		} while (newPosition.x in 0 until map.columns && newPosition.y in 0 until map.lines)

		return route
	}

	private fun checkLoop(map: Grid, obstacle: Coordinate, initialPosition: Coordinate): Boolean {
		var guardSense = Direction.UP
		var guardPosition = initialPosition
		var newPosition = guardPosition
		val histories = mutableSetOf<Pair<Coordinate, Direction>>()

		do {
			if (map[newPosition.y][newPosition.x] == '#' || newPosition == obstacle) {
				if (newPosition to guardSense in histories) {
					return true
				}
				histories.add(newPosition to guardSense)
				guardSense = guardSense.flip()
			} else {
				guardPosition = newPosition
			}
			newPosition = guardPosition + guardSense.movement
		} while (newPosition.x in 0 until map.columns && newPosition.y in 0 until map.lines)

		return false
	}

	private fun findSymbolsOnMap(map: Grid): Coordinate {
		for (y in 0 until map.lines) {
			for (x in 0 until map.columns) {
				when (map[y][x]) {
					'^' -> return Coordinate(x, y)
				}
			}
		}
		throw Exception("Guard Coordinate should not null!")
	}

	enum class Direction(val value: Int, val movement: Coordinate) {
		UP(0,    Coordinate(0, -1)),
		RIGHT(1, Coordinate(1, 0)),
		DOWN(2,  Coordinate(0, 1)),
		LEFT(3,  Coordinate(-1, 0));

		fun flip(): Direction = fromInt((value + 1).let { if (it > 3) 0 else it })

		companion object {
			fun fromInt(value: Int) = Direction.values().first { it.value == value }
		}
	}

	data class Coordinate(var x: Int, var y: Int) {
		operator fun plus(increment: Coordinate): Coordinate = Coordinate(x + increment.x, y + increment.y)
	}

	data class Grid(val map: List<CharArray>) {
		val lines = map.size
		val columns = map[0].size
		operator fun get(index: Int): CharArray = map[index]
	}
}