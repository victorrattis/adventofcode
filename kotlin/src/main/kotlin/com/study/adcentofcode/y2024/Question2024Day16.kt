package com.study.adcentofcode.y2024

class Question2024Day16: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val map = input.split(System.lineSeparator()).map { it.toCharArray() }

		var sense: Coordinate = Coordinate.LEFT
		var start = Coordinate(0, 0)
		var end = Coordinate(0, 0)
		val path = HashMap<Coordinate, MutableSet<Coordinate>>()

		foreachMap(map) { x, y, tile ->
			when (tile) {
				'S' -> {
					start = Coordinate(x, y)
					path[Coordinate(x, y)] = mutableSetOf()
				}

				'E' -> {
					end = Coordinate(x, y)
					path[Coordinate(x, y)] = mutableSetOf()
				}

				'.' -> path[Coordinate(x, y)] = mutableSetOf()
			}
		}

		path.keys.forEach { current ->
			if (current + Coordinate.RIGHT in path) {
				path[current]?.add(current + Coordinate.RIGHT)
			}
			if (current + Coordinate.LEFT in path) {
				path[current]?.add(current + Coordinate.LEFT)
			}
			if (current + Coordinate.DOWN in path) {
				path[current]?.add(current + Coordinate.DOWN)
			}
			if (current + Coordinate.UP in path) {
				path[current]?.add(current + Coordinate.UP)
			}
		}

		val graph = mutableListOf<Edge>()
		val nodes = path.filter { it.value.size >= 3 || it.key == start || end == it.key }

		var score = 0

		var diff: Coordinate = Coordinate.RIGHT

		var currentNodes = listOf(start)
		var next = listOf(start)
		var count = 0
		var diffs = listOf<Coordinate>(Coordinate.RIGHT)
		var nextDiffs = mutableListOf(Coordinate.RIGHT)

		do {
			diffs = nextDiffs.toList()
			nextDiffs = mutableListOf()
			currentNodes = next
			next = mutableListOf()

			currentNodes.forEachIndexed { index, node ->
				diff = diffs[index]
				val startNode = path[node]
				startNode?.forEach {
					score = 0
					var previous = node
					var current = it

					var temDiff = current - node
					score++
					if (temDiff != diff) score += 1000

					do {
						val point = path[current]?.filter { it != previous }
						if ((point?.size ?: 0) > 0) {
							val t = point?.get(0)
							temDiff = t!! - current
							score++
							if (temDiff != diff) score += 1000
							diff = temDiff

							if (t in nodes) {
								graph.add(Edge(node, t, score))
								if (t != end) {
									next.add(t)
									nextDiffs.add(diff)
								}
								break
							}
							previous = current
							current = t
						} else {
							break
						}
					} while (true)
				}
			}
			count++
			if (count > 1) break
		} while (next.size > 0)





		println(graph)

//		path.filter { it.key == end }.let {
//			println(it)
//		}



//		path.filter { it.value.size >= 3 && it.key != start }.let {
//			println("size: ${it.size}")
//			it
//		}.forEach {
//			map[it.key.y][it.key.x] = '+'
//		}
//
////		path.filter { it.value.size >= 3 }
//
//		map.forEach { println(it) }
//		println(path.size)

		return ""
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

	interface Node

	data class Edge(val node1: Node, val node2: Node, val distance: Int)

	/**
	 * See https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
	 */
	private fun findShortestPath(edges: List<Edge>, source: Node, target: Node): ShortestPathResult {
		// Note: this implementation uses similar variable names as the algorithm given do.
		// We found it more important to align with the algorithm than to use possibly more sensible naming.

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
				break // Found shortest path to target
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