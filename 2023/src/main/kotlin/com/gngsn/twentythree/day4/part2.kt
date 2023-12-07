package com.gngsn.twentythree.day4

import com.gngsn.twentythree.readFile

fun main() {
    val mark = MutableList(200) { 0 }

    readFile("day4/input.txt")
        .map { game ->
            var w = 0
            var n = 0
            var c = 0

            val row = game.split(':')
            val id = row[0].split(' ').last.toInt()
            row[1]
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

            while (c != 0) {
                val copies = mark[id]
                mark[id + c--] += 1 + copies
            }

            mark[id] + 1
        }
        .sum()
        .let { println(it) }
}

private fun splitSpacing(it: String) =
    it.split(' ').filter { i -> i != "" }.map { n -> n.toInt() }.sorted()
