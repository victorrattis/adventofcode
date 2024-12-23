package com.study.adcentofcode.y2024

import kotlin.math.pow

class Question2024Day17: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val iterator = input.split(System.lineSeparator()).iterator()
		val a = iterator.next().split(": ")[1].trim().toLong()
		val b = iterator.next().split(": ")[1].trim().toLong()
		val c = iterator.next().split(": ")[1].trim().toLong()
		iterator.next()
		val resultProgram = iterator.next().split(": ")[1].trim()
		val programs = resultProgram.split(",").map { it.toInt() }
		val state = ComputerState(a, b, c)

		return if (isPart2) { processPart2(programs) } else { processPart1(state, programs) }
	}

	private fun processPart2(programs: List<Int>): String  {
		var current: Long = 8
		do {
			val result = ComputerState(current)
			result.execute(programs)
			if (programs.takeLast(result.out.size) == result.out) {
				if (programs == result.out) break
				current = current shl 3
			} else {
				current++
			}
		} while (true)
		return current.toString()
	}

	private fun processPart1(state: ComputerState, programs: List<Int>): String =
		state.also { it.execute(programs) }.out.joinToString(",")

	data class ComputerState(
		var a: Long,
		var b: Long = 0,
		var c: Long = 0,
		var pointer: Int = 0,
		var out: MutableList<Int> = mutableListOf()
	) {
		fun execute(programs: List<Int>) {
			while (pointer < programs.size) {
				executeInstruction(programs[pointer], programs[pointer + 1])
			}
		}

		private fun executeInstruction(opcode: Int, operand: Int) {
			when(opcode) {
				0 -> { a = (a / (2.0.pow(getOperandValue(operand).toDouble()))).toLong() }
				1 -> { b = b.xor(operand.toLong()) }
				2 -> { b = getOperandValue(operand) % 8 }
				3 -> {
					if (a != 0L) {
						pointer = operand
						return
					}
				}
				4 -> { b = b.xor(c) }
				5 -> { out.add((getOperandValue(operand) % 8).toInt()) }
				6 -> { b = (a / (2.0.pow(getOperandValue(operand).toDouble()))).toLong() }
				7 -> { c = (a / (2.0.pow(getOperandValue(operand).toDouble()))).toLong() }
				else -> {}
			}
			pointer += 2
		}

		private fun getOperandValue(operand: Int): Long = when(operand) {
			0, 1, 2, 3 -> operand.toLong()
			4 -> a
			5 -> b
			6 -> c
			else -> throw Exception("This is not valid program!")
		}
	}
}