package com.gngsn.year2022.day2

import com.gngsn.year2022.readFile
import java.util.concurrent.atomic.AtomicInteger

fun main(args: Array<String>) {
    val max = AtomicInteger(0)
    val tmp = AtomicInteger(0)

    readFile("src/main/kotlin/day2/input.txt").subscribe { line ->
        if (line.isEmpty()) {
            max.getAndAccumulate(tmp.get(), Math::max)
            tmp.set(0)
        } else tmp.accumulateAndGet(line.toInt(), { x, y -> x + y })
    }

    println("MAX: " + max.get())
}