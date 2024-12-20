package com.study.adcentofcode.y2023

import java.io.File
import kotlin.math.abs

import java.lang.Double.MAX_VALUE
import java.lang.Double.MIN_VALUE
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

typealias CharMatrix = Array<String>

data class Coordinate(val x: Int, val y: Int)

@OptIn(ExperimentalTime::class)
class Question10 {
    fun executeParte1(filePath: String): Long {
        val matrix = loadDataFromFile(filePath)
        var steps = 1L
        val time = measureTime {

            val start: Coordinate = findStartTile(matrix)
            var current = findPossibleFirstPath(matrix, start)
            var previous = start

            while (current != start) {
                current = nextPosition(previous, matrix[current.x][current.y], current).also { previous = current }
                steps++
            }

            steps /= 2
        }
        println("part1: $time")
        return steps
    }

    fun executeParte2(filePath: String): Long {
        val matrix = loadDataFromFile(filePath)
        val lines = matrix.size
        val column = matrix[0].length

        val pathMatrix: Array<Array<Boolean>> = Array(lines) { Array(column) { false } }
        val pathEdge = mutableListOf<Edge>()
        val start: Coordinate = findStartTile(matrix)
        var current = findPossibleFirstPath(matrix, start)
        var previous = start

        pathEdge.add(Edge(start.toPoint(), current.toPoint()))
        pathMatrix[start.x][start.y] = true
        pathMatrix[current.x][current.y] = true

        while(current != start) {
            current = nextPosition(previous, matrix[current.x][current.y], current).also { previous = current }
            pathMatrix[current.x][current.y] = true
            pathEdge.add(Edge(previous.toPoint(), current.toPoint()))
        }

        val figure = Figure(pathEdge.toTypedArray())

        var count = 0L

        val point = Point(0.0, 0.0)
        for (x in 0 until lines) {
            point.x = x.toDouble()
            for (y in 0 until column) {
                if (!pathMatrix[x][y]) {
                    point.y = y.toDouble()
                    if (figure.contains(point)) {
                       count++
                    }
                }
            }
        }

        return count

    }

    fun executeParte2Optimized(filePath: String): Long {
        val matrix = loadDataFromFile(filePath)
        var area = 0.0
        var steps = 1L
        var result = 0L
        val time = measureTime {
            val start: Coordinate = findStartTile(matrix)
            var current = findPossibleFirstPath(matrix, start)
            var previous = start

            area += start.x * current.y - current.x * start.y
            var next: Coordinate
            while(current != start) {
                next = nextPosition(previous, matrix[current.x][current.y], current)
                previous = current
                current = next

                area += previous.x * current.y - current.x * previous.y
                steps++
            }
            result = (abs(area).toLong() / 2) - (steps / 2) + 1
        }
        println("internal: $time")

        // Calculate Are using shoelace formula and als use Pick's theorem
        return result
    }

    private fun nextPosition(previous: Coordinate, tileValue: Char, tile: Coordinate): Coordinate {
        return when(tileValue) {
            '-' -> Coordinate(tile.x, if (tile.y - 1 == previous.y) tile.y + 1 else tile.y - 1 )
            '|' -> Coordinate(if (tile.x - 1 == previous.x) tile.x + 1 else tile.x - 1, tile.y)
            'L' -> if (tile.y == previous.y) Coordinate(tile.x, tile.y + 1) else Coordinate(tile.x - 1, tile.y)
            'J' -> if (tile.y == previous.y) Coordinate(tile.x, tile.y - 1) else Coordinate(tile.x - 1, tile.y)
            '7' -> if (tile.y == previous.y) Coordinate(tile.x, tile.y - 1) else Coordinate(tile.x + 1, tile.y)
            'F' -> if (tile.y == previous.y) Coordinate(tile.x, tile.y + 1) else Coordinate(tile.x + 1, tile.y)
            else -> Coordinate(0, 0)
        }
    }

    private fun findPossibleFirstPath(matrix: CharMatrix, start: Coordinate): Coordinate {
        if (start.y < matrix[start.x].length - 1 && checkConnectionWithStartTile(
                start,
                matrix[start.x][start.y + 1],
                Coordinate(start.x, start.y + 1)
            )) {
            return Coordinate(start.x, start.y + 1)
        }
        if (start.y > 0 && checkConnectionWithStartTile(
                start,
                matrix[start.x][start.y - 1],
                Coordinate(start.x, start.y - 1)
            )) {
            return Coordinate(start.x, start.y - 1)
        }
        if (start.x > 0 && checkConnectionWithStartTile(
                start,
                matrix[start.x - 1][start.y],
                Coordinate(start.x - 1, start.y)
            )) {
            return Coordinate(start.x - 1, start.y)
        }
        if(start.x < matrix.size -1 && checkConnectionWithStartTile(
                start,
                matrix[start.x + 1][start.y],
                Coordinate(start.x + 1, start.y)
            )) {
            return Coordinate(start.x + 1, start.y)
        }
        throw Exception("No Path!")
    }

    private fun checkConnectionWithStartTile(
        start: Coordinate, tileValue: Char, tile: Coordinate
    ): Boolean = when(tileValue) {
        '|' -> start.x != tile.x && start.y == tile.y
        '-' -> start.y != tile.y && start.x == tile.x
        'L' -> (start.y > tile.y && start.x == tile.x) ||
                (start.y == tile.y && start.x < tile.x)
        'J' -> (start.y < tile.y && start.x == tile.x) ||
                (start.y == tile.y && tile.x > start.x)
        '7' -> (start.y < tile.y && start.x == tile.x) ||
                (start.y == tile.y && tile.x < start.x)
        'F' -> (start.y > tile.y && start.x == tile.x) ||
                (start.y == tile.y && tile.x < start.x)
        else -> false
    }

    private fun findStartTile(matrix: CharMatrix): Coordinate {
        for (x in matrix.indices) {
            val line = matrix[x]
            for (y in line.indices) {
                if (line[y] == 'S') {
                    return Coordinate(x, y)
                }
            }
        }

        throw IllegalArgumentException("No S tile!")
    }

    private fun loadDataFromFile(filePath: String): CharMatrix {
        val result = mutableListOf<String>()
        File(filePath).inputStream().bufferedReader().forEachLine { result.add(it) }
        return result.toTypedArray()
    }

    private fun Coordinate.toPoint(): Point = Point(x.toDouble(), y.toDouble())

    // Ray Casting Algorithm ---
    // reference: https://rosettacode.org/wiki/Ray-casting_algorithm#Kotlin
    private data class Point(var x: Double, var y: Double)

    private data class Edge(val s: Point, val e: Point) {
        companion object {
            const val EPSILON = 0.0001
        }

        operator fun invoke(p: Point) : Boolean = when {
            s.y > e.y -> Edge(e, s).invoke(p)
            p.y == s.y || p.y == e.y -> invoke(Point(p.x, p.y + EPSILON))
            p.y > e.y || p.y < s.y || p.x > s.x.coerceAtLeast(e.x) -> false
            p.x < s.x.coerceAtMost(e.x) -> true
            else -> {
                val blue = if (abs(s.x - p.x) > MIN_VALUE) (p.y - s.y) / (p.x - s.x) else MAX_VALUE
                val red = if (abs(s.x - e.x) > MIN_VALUE) (e.y - s.y) / (e.x - s.x) else MAX_VALUE
                blue >= red
            }
        }
    }

    private class Figure(private val edges: Array<Edge>) {
        operator fun contains(p: Point) = edges.count { it(p) } % 2 != 0
    }

    // Shoelace formula for polygonal area ---
    // reference: https://rosettacode.org/wiki/Shoelace_formula_for_polygonal_area#Kotlin
    private fun shoelaceArea(points: Array<Coordinate>): Double {
        val size = points.size
        var area = 0.0
        for (i in 0 until size - 1) {
            area += points[i].x * points[i + 1].y - points[i + 1].x * points[i].y
        }
        return abs(area + points[size - 1].x * points[0].y - points[0].x * points[size -1].y) / 2
    }
}