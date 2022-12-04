package com.gngsn.year2022.day3

import com.gngsn.year2022.readFile

fun main(args: Array<String>) {
    readFile("src/main/kotlin/day3/input.txt")
        .map { line ->
            val subLists: List<String> = line.chunked(line.length / 2)
            subLists[0].chars()
                .distinct()
                .mapToObj { ch -> ch.toChar() }
                .filter(subLists[1]::contains)
                .findFirst()
                .map {ch -> if (ch >= 'a') ch-'a'+1 else ch-'A'+27 }}
        .map { it.get() }
        .reduce { x, y -> x + y}
        .subscribe {x -> println("TOTAL: $x")}
}

