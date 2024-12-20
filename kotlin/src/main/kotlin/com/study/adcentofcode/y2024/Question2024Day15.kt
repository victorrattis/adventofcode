package com.study.adcentofcode.y2024

class Question2024Day15: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val puzzleInput = loadPuzzleInput(input)
		return if (isPart2) processPart2(puzzleInput) else processPart1(puzzleInput)
	}

	private fun processPart2(input: PuzzleInput): String {
		val walls = HashSet<Coordinate>()
		var robot: Coordinate = Coordinate.ZERO

		val boxes = HashSet<Box>()
		val newPuzzle = input.copy(map = toWiderMap(input.map))
		newPuzzle.map.forEachIndexed { y, line ->
			val iterator = line.iterator()
			var x = 0
			while(iterator.hasNext()) {
				val tile = iterator.next()
				when (tile) {
					'#' -> {
						walls.add(Coordinate(x, y))
					}
					'[' -> {
						val start = Coordinate(x, y)
						boxes.add(Box(start, start + Coordinate.RIGHT))
						iterator.next()
						x++
					}
					'@' -> {
						robot = Coordinate(x, y)
					}
				}
				x++
			}
		}

		var movement: Coordinate
		var nextRobot: Coordinate
		input.movements.forEach {
			movement = toCoordinate(it)
			nextRobot = robot + movement

			if (nextRobot !in walls) {
				if (Box.containBox(nextRobot, boxes)) {
					val box = boxes.find { it.start == nextRobot || it.end == nextRobot }
					moveBox2(box!!, movement, boxes, walls)
				}
				if (!Box.containBox(nextRobot, boxes)) {
					robot = nextRobot
				}
			}
		}
		return boxes.sumOf { (100 * it.start.y) + it.start.x }.toString()
	}

	private fun moveBox2(box: Box, movement: Coordinate, boxes: HashSet<Box>, walls: HashSet<Coordinate>) {
		val nextBox = box.copy(start = box.start + movement, end = box.end + movement)
		if (nextBox.start in walls || nextBox.end in walls) return

		if (!checkIsPossibleBoxMoves(box, movement, boxes, walls)) return

		val foundBoxes = boxes
			.filter { it.start == nextBox.start || it.start == nextBox.end || it.end == nextBox.start || it.end == nextBox.end }
			.filter { it != box }
		if (foundBoxes.isNotEmpty() && box !in foundBoxes) {
			foundBoxes.forEach {
				moveBox2(it, movement, boxes, walls)
			}
		}

		val foundBox = boxes.find { it.start == nextBox.start || it.start == nextBox.end || it.end == nextBox.start || it.end == nextBox.end }
		if (foundBox == null || foundBox == box) {
			boxes.remove(box)
			boxes.add(nextBox)
		}
	}

	private fun checkIsPossibleBoxMoves(box: Box, movement: Coordinate, boxes: HashSet<Box>, walls: HashSet<Coordinate>): Boolean {
		val nextBox = box.copy(start = box.start + movement, end = box.end + movement)
		if (nextBox.start in walls || nextBox.end in walls) return false

		val foundBoxes = boxes
			.filter { it.start == nextBox.start || it.start == nextBox.end || it.end == nextBox.start || it.end == nextBox.end }
			.filter { it != box }
		if (foundBoxes.isNotEmpty() && box !in foundBoxes) {
			return foundBoxes.all() { checkIsPossibleBoxMoves(it, movement, boxes, walls) }
		}

		return true
	}

	private fun processPart1(input: PuzzleInput): String {
		val walls = HashSet<Coordinate>()
		val tileBoxes = HashSet<Coordinate>()
		var robot: Coordinate = Coordinate.ZERO

		input.map.forEachIndexed { y, line ->
			line.forEachIndexed { x, tile ->
				when(tile) {
					'#' -> { walls.add(Coordinate(x, y)) }
					'O' -> { tileBoxes.add(Coordinate(x, y)) }
					'@' -> { robot = Coordinate(x, y) }
				}
			}
		}
		var movement: Coordinate
		var nextRobot: Coordinate
		input.movements.forEach {
			movement = toCoordinate(it)
			nextRobot = robot + movement

			if (nextRobot !in walls) {
				if (nextRobot in tileBoxes) {
					moveBox(nextRobot, movement, tileBoxes, walls)
				}

				if (nextRobot !in tileBoxes) {
					robot = nextRobot
				}
			}
		}
		return tileBoxes.sumOf { (100 * it.y) + it.x }.toString()
	}

	private fun toWiderMap(map: List<CharArray>):  List<CharArray> {
		val newMap = mutableListOf<MutableList<Char>>()
		map.forEachIndexed { y, line ->
			newMap.add(mutableListOf())
			line.forEachIndexed { _, tile ->
				when(tile) {
					'O' -> {
						newMap[y].add('[')
						newMap[y].add(']')
					}
					'#' -> {
						newMap[y].add('#')
						newMap[y].add('#')
					}
					'.' -> {
						newMap[y].add('.')
						newMap[y].add('.')
					}
					'@' -> {
						newMap[y].add('@')
						newMap[y].add('.')
					}
				}
			}
		}
		return newMap.map { it.toCharArray() }
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

	private data class Box(val start: Coordinate, val end: Coordinate) {
		companion object {
			fun containBox(point: Coordinate, boxes: HashSet<Box>): Boolean {
				return boxes.any { it.start == point || it.end == point }
			}
		}
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