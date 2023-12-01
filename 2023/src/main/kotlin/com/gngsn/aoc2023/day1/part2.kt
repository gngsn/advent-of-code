package com.gngsn.year2023.day1

import com.gngsn.year2023.readFile
import java.util.concurrent.atomic.AtomicInteger

fun main(args: Array<String>) {
    val ranking = mutableListOf<Int>()
    val tmp = AtomicInteger(0)

    readFile("src/main/kotlin/com.gngsn.aoc2023.day1/input.txt")
        .doOnNext { line ->
            if (line.isEmpty()) {
                ranking.add(tmp.get())
                tmp.set(0)
            } else tmp.accumulateAndGet(line.toInt(), { x, y -> x + y })
        }.doOnComplete {
            if (tmp.get() != 0) ranking.add(tmp.get())
            println("MAX: " + ranking.sorted().reversed().take(3).stream().reduce(0, Int::plus))
        }.subscribe()

}