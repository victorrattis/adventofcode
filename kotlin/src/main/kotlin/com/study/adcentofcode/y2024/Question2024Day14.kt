package com.study.adcentofcode.y2024

import kotlin.math.ceil

class Question2024Day14: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val robots = input.split(System.lineSeparator()).map { Robot.parse(it) }
		var wide = 0
		var tall = 0
		robots.forEach {
			if (wide < it.start.x) wide = it.start.x
			if (tall < it.start.y) tall = it.start.y
		}
		val size = Coordinate(wide + 1, tall + 1)

		val quadrants: List<MutableMap<Coordinate, Int>> =
			listOf(mutableMapOf(), mutableMapOf(), mutableMapOf(), mutableMapOf())

		println("Robots: ${robots.size}, $size")

		val times = 100
		robots.map {
			((it.start + (it.velocity * times)) % size).let {
				it.copy(
					x=if (it.x < 0) size.x + it.x else it.x,
					y=if (it.y < 0) size.y + it.y else it.y
				)
			}
		}.forEach { position ->
			val middleX = (size.x / 2.0).let { it.toInt() to ceil(it).toInt() }
			val middleY = (size.y / 2.0).let { it.toInt() to ceil(it).toInt() }
			val quadrantIndex: Int? =
				if (position.x in 0 until middleX.first && position.y in 0 until middleY.first) {
					0
				} else if(position.x in middleX.second until size.x && position.y in 0 until middleY.first){
					1
				} else if(position.x in 0 until middleX.first && position.y in middleY.second until size.y){
					2
				} else if(position.x in middleX.second until size.x && position.y in middleY.second until size.y){
					3
				} else {
					null
				}
			quadrantIndex?.let {
				quadrants[it][position] = (quadrants[it][position] ?: 0) + 1
			}
		}

		var sum = 1
		quadrants.forEach { sum *= it.values.sum() }
		return sum.toString()
	}

	private data class Robot(val start: Coordinate, val velocity: Coordinate) {
		companion object {
			fun parse(text: String): Robot =
				text.split(" ").let { Robot(Coordinate.parse(it[0]), Coordinate.parse(it[1])) }
		}
	}

	private data class Coordinate(val x: Int, val y: Int) {
		operator fun plus(value: Coordinate): Coordinate = this.copy(x = x + value.x, y = y + value.y)
		operator fun times(value: Int): Coordinate = this.copy(x = x * value, y = y * value)
		operator fun rem(value: Coordinate): Coordinate = this.copy(x = x % value.x, y = y % value.y)
		companion object {
			fun parse(text: String): Coordinate {
				return text.split("=")[1].split(",").map { it.toInt() }.let { Coordinate(it[0], it[1]) }
			}
		}
	}
}