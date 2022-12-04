package com.gngsn.year2022.day4

import com.gngsn.year2022.readFile

fun main(args: Array<String>) {
    readFile("src/main/kotlin/day4/input.txt")
        .map { line -> line.split(",", "-").map { c -> c.toInt() } }
        .filter { r -> !(r[1] < r[2] || r[0] > r[3]) }
        .count()
        .subscribe {x -> println("$x")}
}