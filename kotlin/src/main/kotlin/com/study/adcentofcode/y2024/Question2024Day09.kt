package com.study.adcentofcode.y2024

class Question2024Day09: Question() {
	override fun executeInput(input: String, isPart2: Boolean): String {
		val blocks = input.map { it.digitToInt() }
		if (isPart2) {
			moveFileWholeBlocksIntoFreeSpace(blocks)
			return ""
		} else {
			val disk = generateBlocksInDisk(blocks)
			moveFilePartsOfBlocksIntoFreeSpace(disk)
			return calculateChecksum(disk).toString()
		}
	}

	private fun calculateChecksum(disk: Array<Int>): Long {
		var sum = 0L
		disk.filter { it != -1 }.forEachIndexed { index, value ->
			sum += index * value
		}
		return sum
	}

	private fun generateBlocksInDisk(blocks: List<Int>): Array<Int>{
		val size: Int = blocks.sum()
		val disk: Array<Int> = Array(size) { -1 }
		var indexWrite = 0
		var blockIndex = 0
		blocks.forEachIndexed { index, number ->
			if (index % 2 == 0) {
				for (i in 0 until number) disk[indexWrite + i] = blockIndex
				indexWrite += number
				blockIndex++
			} else {
				indexWrite += number
			}
		}
		return disk
	}

	private fun moveFilePartsOfBlocksIntoFreeSpace(disk: Array<Int>) {
		var start = 0
		var end = disk.size -1
		do {
			if (disk[start] == -1 && disk[end] != -1) {
				val swap = disk[start]
				disk[start] = disk[end]
				disk[end] = swap
				start++
				end--
			}
			if (disk[start] != -1) start++
			if (disk[end] == -1) end--
		} while (start < end)
	}

	private fun moveFileWholeBlocksIntoFreeSpace(blocks: List<Int>) {
		val freeSpaceBlocks: MutableList<Pair<Int, Int>> = mutableListOf()
		val fileBlocks: MutableMap<Int, Pair<Int, Int>> = mutableMapOf()

		var currentIndex = 0
		var fileId = 0
		blocks.forEachIndexed { index, number ->
			if (index % 2 == 0) {
				fileBlocks[currentIndex] = fileId to number
				fileId++
			} else {
				freeSpaceBlocks.add(currentIndex to number)
			}
			currentIndex += number
		}

		println(freeSpaceBlocks)
		println(fileBlocks)
//
//		val size: Int = blocks.sum()
//		val disk: Array<Int> = Array(size) { -1 }


//		var start = 0
//		var end = disk.size -1
//		do {
//			if (disk[start] == -1 && disk[end] != -1) {
//				val swap = disk[start]
//				disk[start] = disk[end]
//				disk[end] = swap
//				start++
//				end--
//			}
//			if (disk[start] != -1) start++
//			if (disk[end] == -1) end--
//		} while (start < end)
	}
}