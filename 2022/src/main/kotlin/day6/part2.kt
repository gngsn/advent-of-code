package com.gngsn.year2022.day6

import com.gngsn.year2022.readFile
import java.util.concurrent.atomic.AtomicInteger


fun main(args: Array<String>) {
    val WINDOW_SIZE = AtomicInteger(14)
    readFile("src/main/kotlin/day6/input.txt")
        .doOnNext { line ->
            var i = -1;

            while(++i < line.length)
                if (line.slice(i until i + WINDOW_SIZE.get()).toList().distinct().size == WINDOW_SIZE.get()) break;

            println("answer: ${i+WINDOW_SIZE.get()}")
        }.subscribe()
}