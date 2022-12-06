package com.gngsn.year2022.day6

import com.gngsn.year2022.readFile
import reactor.core.publisher.Flux

fun main(args: Array<String>) {
    readFile("src/main/kotlin/day6/input.txt")
        .subscribe { line ->
            println("answer: ${howManyMessageSkipped(line, 4)}")
        }
}

fun howManyMessageSkipped(line: String, size: Int): Int? {
    return Flux.range(0, line.length)
        .filter { i ->
            line.slice(i until i + size).toList().distinct().size == size
        }.blockFirst()?.plus(size)
}

