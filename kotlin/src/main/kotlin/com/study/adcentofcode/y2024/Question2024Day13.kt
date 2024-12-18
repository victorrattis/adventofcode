package com.study.adcentofcode.y2024

import kotlin.math.absoluteValue

class Question2024Day13: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val iterator = input.split(System.lineSeparator()).iterator()

		val clawMachines = mutableListOf<ClawMachine>()
		while (iterator.hasNext()) {
			clawMachines.add(ClawMachine.parse(iterator.next(), iterator.next(), iterator.next()))
			if (iterator.hasNext()) iterator.next()
		}

		val tokens = mutableListOf<Long>()
		clawMachines.forEach {
			val prize = if (isPart2) it.Prize.copy(it.Prize.x + 10000000000000, it.Prize.y + 10000000000000) else it.Prize
			println(prize)
			calculateToken(it.buttonA.x, it.buttonA.y, it.buttonB.x, it.buttonB.y, prize.x, prize.y)?.let {
				tokens.add(it.first * 3 + it.second)
			}
		}

		return tokens.sum().toString()
	}

	private fun calculateToken(xa: Long, ya: Long, xb: Long, yb: Long, xPrize: Long, yPrize: Long): Pair<Long, Long>? {
		val a = ((xPrize*yb) - (xb*yPrize)) / ((yb*xa) - (xb*ya)).toFloat()
		val b = (yPrize - (ya * a)) / yb.toFloat()
		println("A: $a, B: $b")
		println("A: ${a % 1.0 == 0.0}, B: ${b % 1.0 == 0.0}")
		println("A: ${a.absoluteValue % 1.0 >= 0.005}, B: ${b.absoluteValue % 1.0 >= 0.005}")
		println("A: ${factor100(a)}, B: ${factor100(b)}")

		return if (a % 1.0 == 0.0 && b % 1.0 == 0.0) (a.toLong() to b.toLong()) else null
	}

	fun factor100(n: Number) = n.toDouble() % 10000000000000.0 == 0.0

	private data class ClawMachine(val buttonA: Coordinate, val buttonB: Coordinate, val Prize: Coordinate) {
		companion object {
			fun parse(buttonA: String, buttonB: String, prize: String): ClawMachine = ClawMachine(
				parseCoordinate(buttonA), parseCoordinate(buttonB), parseCoordinate(prize)
			)

			private fun parseCoordinate(value: String) =
				Regex("[0-9]+").findAll(value).map { it.value.toLong() }.toList().let { Coordinate(it[0], it[1]) }
		}
	}

	private data class Coordinate(val x: Long, val y: Long)
}