package com.gngsn.year2022.day6

import com.gngsn.year2022.readFile
import java.util.concurrent.atomic.AtomicBoolean

fun main(args: Array<String>) {
    readFile("src/main/kotlin/day6/input.txt")
        .doOnNext { line ->
            var WINDOW_SIZE = 4
            var i = 0;

            while(++i < line.length)
                if (line.slice(i until i+4).toList().distinct().size == 4) break;

            println("answer i: ${i+4}")
        }.subscribe()
}