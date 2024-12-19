package com.study.adcentofcode.y2024

class Question2024Day15: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val puzzleInput = loadPuzzleInput(input)

		val walls = HashSet<Coordinate>()
		val boxes = HashSet<Coordinate>()
		var robot: Coordinate = Coordinate.ZERO

		if (isPart2) {

		} else {
			puzzleInput.map.forEachIndexed { y, line ->
				line.forEachIndexed { x, tile ->
					when(tile) {
						'#' -> { walls.add(Coordinate(x, y)) }
						'O' -> { boxes.add(Coordinate(x, y)) }
						'@' -> { robot = Coordinate(x, y) }
					}
				}
			}
		}



		var movement: Coordinate
		var nextRobot: Coordinate
		puzzleInput.movements.forEach {
			movement = toCoordinate(it)
			nextRobot = robot + movement

			if (nextRobot !in walls) {
				if (nextRobot in boxes) {
					moveBox(nextRobot, movement, boxes, walls)
				}

				if (nextRobot !in boxes) {
					robot = nextRobot
				}
			}
		}

		return boxes.sumOf { (100 * it.y) + it.x }.toString()
	}

	private fun moveBox(box: Coordinate, movement: Coordinate, boxes: HashSet<Coordinate>, walls: HashSet<Coordinate>) {
		val nextBox = box + movement
		if (nextBox in walls) return
		if (nextBox in boxes) moveBox(nextBox, movement, boxes, walls)

		if (nextBox !in boxes) {
			boxes.remove(box)
			boxes.add(nextBox)
		}
	}

	private fun toCoordinate(move: Char): Coordinate = when(move) {
		'>' ->  Coordinate.RIGHT
		'<' ->  Coordinate.LEFT
		'^' ->  Coordinate.UP
		'v' ->  Coordinate.DOWN
		else -> Coordinate.ZERO
	}

	private fun loadPuzzleInput(input: String): PuzzleInput {
		val map = mutableListOf<CharArray>()
		val movements: MutableList<Char> = mutableListOf()

		val iterator = input.split(System.lineSeparator()).iterator()

		var line = iterator.next()
		while (line.isNotBlank()) {
			map.add(line.toCharArray())
			line = iterator.next()
		}

		while (iterator.hasNext()) {
			movements.addAll(iterator.next().toList())
		}
		return PuzzleInput(map, movements)
	}

	private data class PuzzleInput(val map: List<CharArray>, val movements: List<Char>)
	private data class Coordinate(val x: Int, val y: Int) {
		operator fun plus(value: Coordinate): Coordinate = this.copy(x = x + value.x, y = y + value.y)
		companion object {
			val UP = Coordinate(0, -1)
			val DOWN = Coordinate(0, 1)
			val RIGHT = Coordinate(1, 0)
			val LEFT = Coordinate(-1, 0)
			val ZERO = Coordinate(0, 0)
		}
	}
}