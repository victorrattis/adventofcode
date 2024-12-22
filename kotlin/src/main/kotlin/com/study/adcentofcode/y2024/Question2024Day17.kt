package com.study.adcentofcode.y2024

import kotlin.math.pow

class Question2024Day17: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val iterator = input.split(System.lineSeparator()).iterator()
		val a = iterator.next().split(": ")[1].trim().toInt()
		val b = iterator.next().split(": ")[1].trim().toInt()
		val c = iterator.next().split(": ")[1].trim().toInt()
		iterator.next()
		val resultProgram = iterator.next().split(": ")[1].trim()
		val programs = resultProgram.split(",").map { it.toInt() }
		val state = ComputerState(a, b, c)

		return if (isPart2) {
//
//			for (i in 0..117440){
				processPart1(state.copy(a = 1317458136), "2,4,1,5,7,5,1,6,4,1,5,5,0,3,3,0".split(",").map { it.toInt() }).let { println(it) }

//			}

			val a = ComputerState(0, 0, 0)
			listOf(2,4,1,5,7,5,1,6,4,1,5,5,0,3,3,0).forEach {
//				listOf(2,4,1,5,7,5,1,6,4,1,5,5,0,3).reversed().forEach {  }
//				2,4,
				//				1,5,
				//				7,5,
				//				1,6,
				//				4,1,
				//				5,5,
				//				0,3

				inverseInstructions(a, 0, 3, it)
				inverseInstructions(a, 5, 5, it)
				inverseInstructions(a, 1, 6, it)
				inverseInstructions(a, 7, 5, it)
				inverseInstructions(a, 1, 5, it)
				inverseInstructions(a, 2, 4, it)
			}
			println(a)

			a.a.toString()
		} else {
			processPart1(state, programs)
		}
	}

	private fun processPart1(state: ComputerState, programs: List<Int>): String {
		while (state.pointer < programs.size) {
			executeInstruction(
				state,
				programs[state.pointer],
				programs[state.pointer + 1]
			)
		}
		return state.out.joinToString(",")
	}

	private fun inverseInstructions(state: ComputerState, opcode: Int, operand: Int, out: Int) {
		when(opcode) {
			0 -> {
				if (state.a == 0) {
					state.a = 1
				} else {
					state.a *= 2.0.pow(getOperandValue(state, operand).toDouble()).toInt()
				}
			}
			1 -> {
				state.b = operand.xor(state.b)
			}
			2 -> {

				state.b = getOperandValue(state, operand) * 8
//				state.b = getOperandValue(state, operand) % 8
			}
			4 -> {
				state.b = state.c.xor(state.b)
			}
			5 -> {
				state.a += out - (state.a % 8)
			}
			7 -> {
				state.c = state.a * 2.0.pow(getOperandValue(state, operand).toDouble()).toInt()
//				state.c = (state.a / (2.0.pow(getOperandValue(state, operand).toDouble()))).toInt()
			}
			else -> {}
		}
	}

	private fun executeInstruction(state: ComputerState, opcode: Int, operand: Int) {
		when(opcode) {
			0 -> { state.a = (state.a / (2.0.pow(getOperandValue(state, operand).toDouble()))).toInt() }
			1 -> { state.b = state.b.xor(operand) }
			2 -> { state.b = getOperandValue(state, operand) % 8 }
			3 -> {
				if (state.a != 0) {
					state.pointer = operand
					return
				}
			}
			4 -> { state.b = state.b.xor(state.c) }
			5 -> { state.out.add(getOperandValue(state, operand) % 8) }
//			6 -> { state.b = (state.a / (2.0.pow(getOperandValue(state, operand).toDouble()))).toInt() }
			7 -> { state.c = (state.a / (2.0.pow(getOperandValue(state, operand).toDouble()))).toInt() }
			else -> {}
		}
		state.pointer += 2
	}

	private fun getOperandValue(state: ComputerState, operand: Int): Int  = when(operand) {
		0, 1, 2, 3 -> operand
		4 -> state.a
		5 -> state.b
		6 -> state.c
		else -> throw Exception("This is not valid program!")
	}

	data class ComputerState(var a: Int, var b: Int, var c: Int, var pointer: Int = 0, var out: MutableList<Int> = mutableListOf())
}