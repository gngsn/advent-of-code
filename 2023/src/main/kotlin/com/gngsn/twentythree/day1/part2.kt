package com.gngsn.twentythree.day1

import com.gngsn.twentythree.readFile

val m = mapOf(
    'o' to listOf(Digits.one),
    't' to listOf(Digits.two, Digits.three),
    's' to listOf(Digits.six, Digits.seven),
    'f' to listOf(Digits.four, Digits.five),
    'e' to listOf(Digits.eight),
    'n' to listOf(Digits.nine),
)

fun makeStringAllDigit(str: String): String {
    var i = -1
    val sb = StringBuilder(str.length)

    while (++i < str.length) {
        sb.append(
            m[str[i]]?.find { d ->
                i + d.name.length - 1 < str.length && d.name == str.substring(i, i + d.name.length)
            }?.let {
                i += it.name.length - 1
                it.num
            } ?: str[i]
        )
    }

    return sb.toString()
}

fun main() {

    readFile("day1/input.txt")
        .map { makeStringAllDigit(it) }
        .map {
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

enum class Digits(val num: Int) {
    one(1), two(2), three(3), four(4), five(5), six(6), seven(7), eight(8), nine(9);
}