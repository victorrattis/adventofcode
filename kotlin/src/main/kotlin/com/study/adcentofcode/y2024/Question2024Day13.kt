package com.study.adcentofcode.y2024

class Question2024Day13: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String =
		convertToClawMachines(input.split(System.lineSeparator()))
			.map { if (isPart2) errorConversionIntoPrize(it) else it }
			.mapNotNull { calculateTimes(it.buttonA, it.buttonB, it.prize) }
			.sumOf { it.first * 3 + it.second }
			.toString()

	private fun convertToClawMachines(lines: List<String>): List<ClawMachine> {
		val clawMachines = mutableListOf<ClawMachine>()
		val iterator = lines.iterator()
		while (iterator.hasNext()) {
			clawMachines.add(ClawMachine.parse(iterator.next(), iterator.next(), iterator.next()))
			if (iterator.hasNext()) iterator.next()
		}
		return clawMachines
	}

	private fun errorConversionIntoPrize(claw: ClawMachine): ClawMachine =
		claw.copy(prize = Coordinate(claw.prize.x + 10000000000000, claw.prize.y + 10000000000000))

	private fun calculateTimes(buttonA: Coordinate, buttonB: Coordinate, prize: Coordinate): Pair<Long, Long>? {
		val a = (((prize.x*buttonB.y) - (buttonB.x*prize.y)) / ((buttonB.y*buttonA.x) - (buttonB.x*buttonA.y)))
		val b = (prize.y - (buttonA.y * a)) / buttonB.y

		val aRem = ((prize.x*buttonB.y) - (buttonB.x*prize.y)) % ((buttonB.y*buttonA.x) - (buttonB.x*buttonA.y))
		val bRem = (prize.y - (buttonA.y * a)) % buttonB.y

		return if (aRem == 0L && bRem == 0L) a to b else null
	}

	private data class ClawMachine(val buttonA: Coordinate, val buttonB: Coordinate, val prize: Coordinate) {
		companion object {
			fun parse(buttonA: String, buttonB: String, prize: String): ClawMachine = ClawMachine(
				parseCoordinate(buttonA), parseCoordinate(buttonB), parseCoordinate(prize)
			)
			private fun parseCoordinate(value: String) =
				Regex(NUMBER_REGEX).findAll(value).map { it.value.toLong() }.toList().let { Coordinate(it[0], it[1]) }
		}
	}

	private data class Coordinate(val x: Long, val y: Long)

	companion object {
		const val NUMBER_REGEX = "[0-9]+"
	}
}