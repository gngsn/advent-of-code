package com.gngsn.twentythree.day1

import com.gngsn.twentythree.readFile

fun main() {
    readFile("day1/input.txt")
        .map<String, Int> {
            var s = 0
            var e = it.length - 1

            while (!(it[s].isDigit() && it[e].isDigit())) {
                if (!it[s].isDigit()) s++
                if (!it[e].isDigit()) e--
            }
            return@map "${it[s]}${it[e]}".toInt()
        }
        .sum()
        .let { println(it) }
}