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

            val str = list.reduce { s1, s2 ->
                s1.chars().distinct()
                    .mapToObj(Int::toChar)
                    .filter { s -> s2.contains(s) }
                    .collect(Collectors.toList())
                    .joinToString("")
            }

            total.getAndAdd(if (str[0] >= 'a') str[0] - 'a' + 1 else str[0] - 'A' + 27)
            list = mutableListOf()
        }.subscribe()

    println("TOTAL: $total")
}

