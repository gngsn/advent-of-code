package com.gngsn.year2022.day1

import com.gngsn.year2022.readFile
import java.util.concurrent.atomic.AtomicInteger

fun main(args: Array<String>) {
    val max = AtomicInteger(0)
    val tmp = AtomicInteger(0)

    readFile("src/main/kotlin/day1/input.txt")
        .doOnNext { line ->
            if (line.isEmpty()) {
                updateMax(max, tmp)
                tmp.set(0)
            } else tmp.accumulateAndGet(line.toInt(), { x, y -> x + y })
        }.doOnComplete {
            if (tmp.get() != 0) updateMax(max, tmp)
        }.subscribe()

    println("MAX: " + max.get())
}

fun updateMax(max: AtomicInteger, tmp: AtomicInteger) {
    max.getAndAccumulate(tmp.get(), Math::max)
}