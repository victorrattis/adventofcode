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
		var guardPosition = findSymbolsOnMap(map)
		val lines = map.size
		val columns = map[0].size
		var newPosition: Coordinate = guardPosition

		val histories: MutableList<Coordinate> = mutableListOf()

		do {
			val title = map[newPosition.y][newPosition.x]
			if (title == '#') {
				map[guardPosition.y][guardPosition.x] = '+'
				guardSense = guardSense.flip()
			} else {
				if (title == '^') {

				} else if (title == '|' && (guardSense == Sense.LEFT || guardSense == Sense.RIGHT)) {
					map[newPosition.y][newPosition.x] = '+'
				} else if (title == '-' && (guardSense == Sense.UP || guardSense == Sense.DOWN)) {
					map[newPosition.y][newPosition.x] = '+'
				} else {
					map[newPosition.y][newPosition.x] = when (guardSense) {
						Sense.UP, Sense.DOWN -> '|'
						Sense.RIGHT, Sense.LEFT -> '-'
					}
				}
				guardPosition = newPosition
			}
			newPosition = guardPosition + guardSense.movement
		} while (newPosition.x in 0 until columns && newPosition.y in 0 until lines)

		map.forEach {
			println(it)
		}

		return 0 //map.sumOf { it.count { tile -> tile == 'X' } }
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

	data class Coordinate(var x: Int, var y: Int) {
		operator fun plus(increment: Coordinate): Coordinate = Coordinate(
			x + increment.x, y + increment.y
		)
	}
}