package com.study.adcentofcode.y2024

import kotlin.math.max

class Question2024Day18: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val points: List<Coordinate> = input.split(System.lineSeparator())
			.map { it.split(",").map { it.toInt() }.let { Coordinate(it[0], it[1]) } }

		val size = points.reduce { acc, coordinate ->
			Coordinate(max(acc.x, coordinate.x), max(acc.y, coordinate.y))
		} + 1

		val map = mutableListOf<CharArray>()
		(0 until size.y).forEach { _ -> map.add(CharArray(size.x) { '.' }) }
		val max = if (size.x > 10) 1024 else 12

		val start = Coordinate.ZERO
		val end = size - 1

		return if (isPart2) processPart2(map, start, end, points, max) else processPart1(map, start, end, points, max)
	}

	private fun processPart2(
		map: List<CharArray>,
		start: Coordinate,
		end: Coordinate,
		walls: List<Coordinate>,
		max: Int
	): String {
		var prevent: Coordinate? = null

		val minimalWalls = walls.take(max)
		var graph = generateGraph(map)
		graph = graph.filter { it.node1 as Coordinate !in minimalWalls && it.node2 as Coordinate !in minimalWalls }.toMutableList()

		var result = findShortestPath(graph, start, end)
		var lowestPath = result.shortestPath()
		var index = max

		while (prevent == null) {
			val current = walls[index]
			graph.removeIf { it.node1 == current || it.node2 == current  }
			if (current in lowestPath) {
				result = findShortestPath(graph.toList(), start, end)
				val distance = result.shortestDistance()
				if ((distance ?: -1) < 0) {
					prevent = current
				} else {
					lowestPath = result.shortestPath()
				}
			}
			index++
		}
		return "${prevent.x},${prevent.y}"
	}

	private fun generateGraph(map: List<CharArray>): MutableList<Edge>  {
		fun isTileFree(coordinate: Coordinate): Boolean = try {
			map[coordinate.y][coordinate.x] != '#'
		} catch (e: Exception) {
			false
		}
		val graph = mutableListOf<Edge>()
		foreachMap(map) { x, y, tile ->
			if (tile != '#') {
				val current = Coordinate(x, y)
				if (isTileFree(current + Coordinate.UP)) {
					graph.add(Edge(current, current + Coordinate.UP, 1))
				}
				if (isTileFree(current + Coordinate.DOWN)) {
					graph.add(Edge(current, current + Coordinate.DOWN, 1))
				}
				if (isTileFree(current + Coordinate.RIGHT)) {
					graph.add(Edge(current, current + Coordinate.RIGHT, 1))
				}
				if (isTileFree(current + Coordinate.LEFT)) {
					graph.add(Edge(current, current + Coordinate.LEFT, 1))
				}
			}
		}
		return graph
	}

	private fun processPart1(
		map: List<CharArray>,
		start: Coordinate,
		end: Coordinate,
		points: List<Coordinate>,
		max: Int
	): String {
		fun isTileFree(coordinate: Coordinate): Boolean = try {
			map[coordinate.y][coordinate.x] != '#'
		} catch (e: Exception) {
			false
		}

		(0 until max).forEach { i ->
			map[points[i].y][points[i].x] = '#'
		}

		val graph = mutableListOf<Edge>()

		foreachMap(map) { x, y, tile ->
			if (tile != '#') {
				val current = Coordinate(x, y)
				if (isTileFree(current + Coordinate.UP)) {
					graph.add(Edge(current, current + Coordinate.UP, 1))
				}
				if (isTileFree(current + Coordinate.DOWN)) {
					graph.add(Edge(current, current + Coordinate.DOWN, 1))
				}
				if (isTileFree(current + Coordinate.RIGHT)) {
					graph.add(Edge(current, current + Coordinate.RIGHT, 1))
				}
				if (isTileFree(current + Coordinate.LEFT)) {
					graph.add(Edge(current, current + Coordinate.LEFT, 1))
				}
			}
		}
		println(graph)
		val result = findShortestPath(graph, start, end)
		return result.shortestDistance().toString()
	}

	private fun foreachMap(map: List<CharArray>, callback: (x: Int, y: Int, tile: Char) -> Unit) {
		map.forEachIndexed { y, line ->
			line.forEachIndexed { x, tile ->
				callback(x, y, tile)
			}
		}
	}
	
	private data class Coordinate(val x: Int, val y: Int): Node {
		operator fun plus(value: Coordinate): Coordinate = this.copy(x = x + value.x, y = y + value.y)
		operator fun plus(value: Int): Coordinate = this.copy(x = x + value, y = y + value)
		operator fun minus(value: Int): Coordinate = this.copy(x = x - value, y = y - value)
		operator fun minus(value: Coordinate): Coordinate = this.copy(x = x - value.x, y = y - value.y)

		companion object {
			val UP = Coordinate(0, -1)
			val DOWN = Coordinate(0, 1)
			val RIGHT = Coordinate(1, 0)
			val LEFT = Coordinate(-1, 0)
			val ZERO = Coordinate(0, 0)
		}

		override fun toString(): String {
			return "($x, $y)"
		}
	}


	/**
	 * Djikstras shortest path algorithm in kotlin
	 * https://gist.github.com/trygvea/6067a744ee67c2f0447c3c7f5b715d62
	 * */
	interface Node

	data class Edge(val node1: Node, val node2: Node, val distance: Int)

	private fun findShortestPath(edges: List<Edge>, source: Node, target: Node): ShortestPathResult {
		val dist = mutableMapOf<Node, Int>()
		val prev = mutableMapOf<Node, Node?>()
		val q = findDistinctNodes(edges)

		q.forEach { v ->
			dist[v] = Integer.MAX_VALUE
			prev[v] = null
		}
		dist[source] = 0

		while (q.isNotEmpty()) {
			val u = q.minByOrNull { dist[it] ?: 0 }
			q.remove(u)

			if (u == target) {
				break
			}
			edges
				.filter { it.node1 == u }
				.forEach { edge ->
					val v = edge.node2
					val alt = (dist[u] ?: 0) + edge.distance
					if (alt < (dist[v] ?: 0)) {
						dist[v] = alt
						prev[v] = u
					}
				}
		}

		return ShortestPathResult(prev, dist, source, target)
	}

	private fun findDistinctNodes(edges: List<Edge>): MutableSet<Node> {
		val nodes = mutableSetOf<Node>()
		edges.forEach {
			nodes.add(it.node1)
			nodes.add(it.node2)
		}
		return nodes
	}

	class ShortestPathResult(val prev: Map<Node, Node?>, val dist: Map<Node, Int>, val source: Node, val target: Node) {
		fun shortestPath(from: Node = source, to: Node = target, list: List<Node> = emptyList()): List<Node> {
			val last = prev[to] ?: return if (from == to) {
				list + to
			} else {
				emptyList()
			}
			return shortestPath(from, last, list) + to
		}

		fun shortestDistance(): Int? {
			val shortest = dist[target]
			if (shortest == Integer.MAX_VALUE) {
				return null
			}
			return shortest
		}
	}
}