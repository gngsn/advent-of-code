package com.gngsn.twentythree.day4

import com.gngsn.twentythree.readFile
import kotlin.math.pow

fun main() {
    readFile("day4/input.txt")
        .mapNotNull { game ->
            var c = -1
            var w = 0
            var n = 0

            game.split(':')[1]
                .split('|')
                .let {
                    val winning = splitSpacing(it.first)
                    val numbers = splitSpacing(it.last)

                    while (w < winning.size && n < numbers.size) {
                        if (winning[w] > numbers[n]) n++
                        else if (winning[w] < numbers[n]) w++
                        else {
                            c++
                            w++
                            n++
                        }
                    }
                }

            if (c == -1) return@mapNotNull null
            2.0.pow(c.toDouble())
        }
        .sum()
        .let { println(it) }
}

private fun splitSpacing(it: String) =
    it.split(' ').filter { i -> i != "" }.map { n -> n.toInt() }.sorted()
