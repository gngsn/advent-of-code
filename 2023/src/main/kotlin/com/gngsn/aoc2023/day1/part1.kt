package com.gngsn.aoc2023.day1

import com.gngsn.aoc2023.readFile
import java.util.concurrent.atomic.AtomicInteger

fun main(args: Array<String>) {
    val max = AtomicInteger(0)
    val tmp = AtomicInteger(0)

    readFile("src/main/kotlin/com.gngsn.aoc2023.day1/input.txt")
        .map {
            if (it.isEmpty()) {
                updateMax(max, tmp)
                tmp.set(0)
            } else tmp.accumulateAndGet(it.toInt(), { x, y -> x + y })
        }
        .doOnComplete {
            if (tmp.get() != 0) updateMax(max, tmp)
        }.subscribe()

    println("MAX: " + max.get())
}

fun updateMax(max: AtomicInteger, tmp: AtomicInteger) {
    max.getAndAccumulate(tmp.get(), Math::max)
}