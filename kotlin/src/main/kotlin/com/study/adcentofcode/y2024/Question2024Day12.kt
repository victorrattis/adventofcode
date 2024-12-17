package com.study.adcentofcode.y2024

class Question2024Day12: Question() {
    override fun executeInput(input: String, isPart2: Boolean): String {
        val map: Array<CharArray> = input.split(System.lineSeparator()).map { it.toCharArray() }.toTypedArray()
        val regions = identifyRegions(map)
        return calculatePrice(regions, isPart2).toString()
    }

    private fun calculatePrice(regions: Map<Char, MutableList<Set<Coordinate>>>, isPart2: Boolean): Int =
        regions.values.sumOf { it.sumOf { it.size * if (isPart2) calculateSides(it) else calculatePerimeter(it) } }

    private fun calculateSides(region: Set<Coordinate>): Int {
        val sides = mutableMapOf<Coordinate, MutableSet<MutableSet<Coordinate>>>()

        fun containPoint(side: Coordinate, point: Coordinate): Boolean {
            return sides[side]?.any { it.contains(point) } == true
        }

        region.forEach {
            if (!region.contains(it + Coordinate.LEFT) && !containPoint(Coordinate.LEFT, it)) {
                val points = mutableSetOf<Coordinate>()
                points.add(it)
                var next = it + Coordinate.UP
                while (region.contains(next) && !region.contains(next + Coordinate.LEFT)) {
                    points.add(next)
                    next += Coordinate.UP
                }
                next = it + Coordinate.DOWN
                while (region.contains(next) && !region.contains(next + Coordinate.LEFT)) {
                    points.add(next)
                    next += Coordinate.DOWN
                }

                if (!sides.containsKey(Coordinate.LEFT)) sides[Coordinate.LEFT] = mutableSetOf()
                sides[Coordinate.LEFT]?.add(points)
            }

            if (!region.contains(it + Coordinate.RIGHT) && !containPoint(Coordinate.RIGHT, it)) {
                val points = mutableSetOf<Coordinate>()
                points.add(it)
                var next = it + Coordinate.UP
                while (region.contains(next) && !region.contains(next + Coordinate.RIGHT)) {
                    points.add(next)
                    next += Coordinate.UP
                }
                next = it + Coordinate.DOWN
                while (region.contains(next) && !region.contains(next + Coordinate.RIGHT)) {
                    points.add(next)
                    next += Coordinate.DOWN
                }

                if (!sides.containsKey(Coordinate.RIGHT)) sides[Coordinate.RIGHT] = mutableSetOf()
                sides[Coordinate.RIGHT]?.add(points)
            }

            if (!region.contains(it + Coordinate.DOWN) && !containPoint(Coordinate.DOWN, it)) {
                val points = mutableSetOf<Coordinate>()
                points.add(it)
                var next = it + Coordinate.RIGHT
                while (region.contains(next) && !region.contains(next + Coordinate.DOWN)) {
                    points.add(next)
                    next += Coordinate.RIGHT
                }
                next = it + Coordinate.LEFT
                while (region.contains(next) && !region.contains(next + Coordinate.DOWN)) {
                    points.add(next)
                    next += Coordinate.LEFT
                }

                if (!sides.containsKey(Coordinate.DOWN)) sides[Coordinate.DOWN] = mutableSetOf()
                sides[Coordinate.DOWN]?.add(points)
            }

            if (!region.contains(it + Coordinate.UP) && !containPoint(Coordinate.UP, it)) {
                val points = mutableSetOf<Coordinate>()
                points.add(it)
                var next = it + Coordinate.RIGHT
                while (region.contains(next) && !region.contains(next + Coordinate.UP)) {
                    points.add(next)
                    next += Coordinate.RIGHT
                }
                next = it + Coordinate.LEFT
                while (region.contains(next) && !region.contains(next + Coordinate.UP)) {
                    points.add(next)
                    next += Coordinate.LEFT
                }

                if (!sides.containsKey(Coordinate.UP)) sides[Coordinate.UP] = mutableSetOf()
                sides[Coordinate.UP]?.add(points)
            }
        }

        return sides.values.sumOf { it.size }
    }

    private fun calculatePerimeter(region: Set<Coordinate>): Int = region.sumOf {
        var perimeter =0
        if (!region.contains(it + Coordinate.UP)) {
            perimeter+=1
        }
        if (!region.contains(it + Coordinate.LEFT)) {
            perimeter+=1
        }
        if (!region.contains(it + Coordinate.RIGHT)) {
            perimeter+=1
        }
        if (!region.contains(it + Coordinate.DOWN)) {
            perimeter+=1
        }
        perimeter
    }

    private fun identifyRegions(map: Array<CharArray>): Map<Char, MutableList<Set<Coordinate>>> {
        val regions = mutableMapOf<Char, MutableList<Set<Coordinate>>>()
        for (x in map.indices) {
            for (y in 0 until map[x].size) {
                val type = map[x][y]
                if (regions.contains(type)) {
                    if (regions[type]?.any { it.contains(Coordinate(x, y)) } == true) {
                        continue
                    }
                } else {
                    regions[type] = mutableListOf()
                }

                mutableSetOf<Coordinate>().apply {
                    checkTypeInRegion(type, Coordinate(x, y), map, this)
                    regions[type]?.add(this)
                }
            }
        }
        return regions
    }

    private fun checkTypeInRegion(
        type: Char,
        point: Coordinate,
        map: Array<CharArray>,
        result: MutableSet<Coordinate>) {
        try {
            if (result.contains(point)) return
            if (map[point.x][point.y] != type) return
            else result.add(point)
        } catch (e: IndexOutOfBoundsException) {
            return
        }

        checkTypeInRegion(type, (point + Coordinate.UP), map, result)
        checkTypeInRegion(type, (point + Coordinate.DOWN), map, result)
        checkTypeInRegion(type, point + Coordinate.RIGHT, map, result)
        checkTypeInRegion(type, point + Coordinate.LEFT, map, result)
    }

    private data class Coordinate(val x: Int, val y: Int) {
        operator fun plus(value: Coordinate): Coordinate = this.copy(x = x + value.x, y = y + value.y)
        companion object {
            val UP = Coordinate(0, -1)
            val DOWN = Coordinate(0, 1)
            val RIGHT = Coordinate(1, 0)
            val LEFT = Coordinate(-1, 0)
        }
    }
}