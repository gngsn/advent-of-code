package com.gngsn.year2022.day3

import com.gngsn.year2022.readFile
import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.Collectors

fun main(args: Array<String>) {
    var list = mutableListOf<String>()
    val total = AtomicInteger(0)

    readFile("src/main/kotlin/day3/input.txt")
        .doOnNext { line ->
            list.add(line)

            if (list.size != 3) return@doOnNext

            total.getAndAdd(list.reduce { s1, s2 ->
                s1.chars().distinct()
                    .mapToObj(Int::toChar)
                    .filter { s -> s2.contains(s) }
                    .collect(Collectors.toList())
                    .joinToString("")
            }.first().toElfDigit())

            list = mutableListOf()
        }.subscribe()

    println("TOTAL: $total")
}

private fun Char.toElfDigit(): Int = if (this >= 'a') this-'a'+ 1 else this-'A' + 27
