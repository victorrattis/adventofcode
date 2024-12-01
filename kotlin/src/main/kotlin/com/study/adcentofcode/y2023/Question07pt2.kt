package com.study.adcentofcode.y2023

import java.io.File

class Question07pt2 {
    fun execute(filePath: String): Int {
        val listOfHands: MutableList<HandDetail> = mutableListOf()
        File(filePath).inputStream().bufferedReader().forEachLine {
            val parts = it.split(" ")
            listOfHands.add(
                HandDetail(
                    cards = parts[0],
                    bidAmount = parts[1].trim().toInt()
                )
            )
        }

        listOfHands.sortWith { h1, h2 ->
            val type1 = h1.calculateType()
            val type2 = h2.calculateType()
            if (type1 > type2) -1
            else if (type1 < type2) 1
            else compareHands(h1.cards, h2.cards)
        }

        return listOfHands.foldIndexed(0) { index, sum, item -> sum + ((index + 1) * item.bidAmount)}
    }

//    A, K, Q, T, 9, 8, 7, 6, 5, 4, 3, 2, J
    private val cardWeightMap: Map<Char, Int> = mapOf(
        'A' to 13,
        'K' to 12,
        'Q' to 11,
        'T' to 9,
        '9' to 8,
        '8' to 7,
        '7' to 6,
        '6' to 5,
        '5' to 4,
        '4' to 3,
        '3' to 2,
        '2' to 1,
        'J' to 0
    )
    private fun compareHands(hand1: String, hand2: String): Int {
        for (i in hand1.indices) {
            if (hand1[i] != hand2[i]) {
                return if (cardWeightMap[hand1[i]]!! > cardWeightMap[hand2[i]]!!) 1 else -1
            }
        }
        throw Exception("$hand1 == $hand2")
    }

    enum class HandType(val value: Int) {
        FiveOfAKind(7),
        FourOfAKind(6),
        FullHouse(5),
        ThreeOfAKind(4),
        TwoPair(3),
        OnePair(2),
        HighCard(1)
    }

    data class HandDetail(
        val cards: String,
        val bidAmount: Int
    ) {
        fun calculateType(): HandType {
            val count = mutableMapOf<Char, Int>()
            var countJoker = 0
            cards.forEach {
                if (it == 'J') {
                    countJoker++
                } else {
                    if (count.containsKey(it)) {
                        count[it] = count[it]!! + 1
                    } else {
                        count[it] = 1
                    }
                }
            }
            if (countJoker == 5) return HandType.FiveOfAKind

            if (countJoker > 0) {
                // get high card
                var high = 0
                var highCard: Char? = null
                count.entries.forEach {
                    if (high < it.value) {
                        high = it.value
                        highCard = it.key
                    }
                }
                if (highCard != null) {
                    count[highCard!!] = count[highCard]!! + countJoker
                }
            }

            return if (count.entries.size == 1) {
                HandType.FiveOfAKind
            } else if (count.entries.size == 2 && isFourOfaKind(count)) {
                HandType.FourOfAKind
            } else if (count.entries.size == 2 && isFullHouse(count)) {
                HandType.FullHouse
            } else if (count.entries.size == 3 && isThreeOfAKind(count)) {
                HandType.ThreeOfAKind
            } else if (count.entries.size == 3 && isTwoPair(count)) {
                HandType.TwoPair
            } else if (count.entries.size == 4 && isTwoPair(count)) {
                HandType.OnePair
            } else {
                HandType.HighCard
            }
        }

        private fun isFourOfaKind(count: Map<Char, Int>) = count.entries
            .fold(true) { sum, current -> sum && (current.value == 4 || current.value == 1) }

        private fun isFullHouse(count: Map<Char, Int>) = count.entries
            .fold(true) { sum, current -> sum && (current.value == 3 || current.value == 2) }

        private fun isThreeOfAKind(count: Map<Char, Int>) = count.entries
            .fold(true) { sum, current -> sum && (current.value == 3 || current.value == 1) }

        private fun isTwoPair(count: Map<Char, Int>) = count.entries
            .fold(true) { sum, current -> sum && (current.value == 2 || current.value == 1) }
    }
}