package com.study.adcentofcode.y2024

class Question2024Day08: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val map = input.split(System.lineSeparator()).map { it.toCharArray() }
		return processAntiNodes(map, isPart2).toString()
	}

	private fun processAntiNodes(map: List<CharArray>, isPart2: Boolean): Int {
		val lines = map.size
		val columns = map[0].size
		val antennas: MutableMap<Char, MutableList<Coordinate>> = mutableMapOf()

		for (y in 0 until columns)
			for (x in 0 until lines) {
				if (map[y][x] != '.') {
					if (!antennas.containsKey(map[y][x])) antennas[map[y][x]] = mutableListOf()
					antennas[map[y][x]]?.add(Coordinate(x, y))
				}
			}

		antennas.entries.forEach { antenna ->
			for (i in 0 until (antenna.value.size - 1)) {
				for (j in i + 1 until (antenna.value.size)) {
					(if (!isPart2)generateAntiNodesLimited(antenna.value[i], antenna.value[j], lines, columns)
					else generateAntiNodes(antenna.value[i], antenna.value[j], lines, columns)).onEach {
						map[it.y][it.x] = '#'
					}
				}
			}
		}
		return map.sumOf { it.count { it == '#' } }
	}

	private fun generateAntiNodesLimited(a: Coordinate, b: Coordinate, lines: Int, columns: Int): List<Coordinate> {
		val diff = a - b
		val next = a + diff
		val previous = b - diff

		val nodes = mutableListOf<Coordinate>()
		if (inRange(next, lines, columns)) nodes.add(next)
		if (inRange(previous, lines, columns)) nodes.add(previous)

		return nodes
	}

	private fun generateAntiNodes(a: Coordinate, b: Coordinate, lines: Int, columns: Int): List<Coordinate> {
		val nodes = mutableListOf<Coordinate>()
		val diff = a - b

		var next = a
		do {
			next += diff
			val isRanged = inRange(next, lines, columns)
			if (isRanged) nodes.add(next)
		} while (isRanged)

		next = b
		do {
			next += diff
			val isRanged = inRange(next, lines, columns)
			if (isRanged) nodes.add(next)
		} while (isRanged)

		var previous = a
		do {
			previous -= diff
			val isRanged = inRange(previous, lines, columns)
			if (isRanged) nodes.add(previous)
		} while (isRanged)

		previous = b
		do {
			previous -= diff
			val isRanged = inRange(previous, lines, columns)
			if (isRanged) nodes.add(previous)
		} while (isRanged)

		return nodes
	}

	private fun inRange(coordinate: Coordinate, lines: Int, columns: Int): Boolean =
		coordinate.y in 0 until columns && coordinate.x in 0 until lines

	data class Coordinate(var x: Int, var y: Int) {
		operator fun plus(value: Coordinate): Coordinate = Coordinate(x + value.x, y + value.y)
		operator fun minus(value: Coordinate): Coordinate = Coordinate(x - value.x, y - value.y)
	}
}