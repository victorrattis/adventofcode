package com.study.adcentofcode

import java.io.File

typealias CharMatrix = Array<String>

data class Coordinate(val x: Int, val y: Int)

class Question10 {
    fun execute(filePath: String, part2: Boolean = false): Long {
        val matrix = loadDataFromFile(filePath)
        val start: Coordinate = findStartTitle(matrix)

        var paths = findPossiblePaths(matrix, start)
        var steps = 1L

        var previous: List<Coordinate> = paths.map { start }

        while (!paths.all { it == paths[0] }) {
            paths = paths.mapIndexed { index, current ->
                nextPosition(previous[index], matrix[current.x][current.y], current)
            }.also { previous = paths }
            steps++
        }

        return steps
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

    private fun findPossiblePaths(matrix: CharMatrix, start: Coordinate): List<Coordinate> {
        val paths: MutableList<Coordinate> = mutableListOf()
        if (start.y < matrix[start.x].length - 1 && checkConnectionWithStartTile(
                start,
                matrix[start.x][start.y + 1],
                Coordinate(start.x, start.y + 1)
            )) {
            paths.add(Coordinate(start.x, start.y + 1))
        }
        if (start.y > 0 && checkConnectionWithStartTile(
                start,
                matrix[start.x][start.y - 1],
                Coordinate(start.x, start.y - 1)
            )) {
            paths.add(Coordinate(start.x, start.y - 1))
        }
        if (start.x > 0 && checkConnectionWithStartTile(
                start,
                matrix[start.x - 1][start.y],
                Coordinate(start.x - 1, start.y)
            )) {
            paths.add(Coordinate(start.x - 1, start.y))
        }
        if(start.x < matrix.size -1 && checkConnectionWithStartTile(
                start,
                matrix[start.x + 1][start.y],
                Coordinate(start.x + 1, start.y)
            )) {
            paths.add(Coordinate(start.x + 1, start.y))
        }
        return paths.toList()
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

    private fun findStartTitle(matrix: CharMatrix): Coordinate {
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
        File(filePath).inputStream().bufferedReader().forEachLine { line ->
            result.add(line)
        }
        return result.toTypedArray()
    }
}