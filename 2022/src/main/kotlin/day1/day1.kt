package com.gngsn.year2022.day1

import reactor.core.publisher.Flux
import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.atomic.AtomicInteger

fun main(args: Array<String>) {
    val inputs: Flux<String> = readFile()
    val max = AtomicInteger(0)
    val tmp = AtomicInteger(0)

    inputs.subscribe { line ->
        if (line.isEmpty()) {
            max.getAndAccumulate(tmp.get(), Math::max)
            tmp.set(0)
        } else tmp.accumulateAndGet(line.toInt(), { x, y -> x + y })
    }

    println("MAX: " + max.get())
}

fun readFile(): Flux<String> {
    val ipPath = Paths.get("src/main/kotlin/day1/input.txt")

    return Flux.using(
        { Files.lines(ipPath) },
        { s -> Flux.fromStream(s) },
        { obj -> obj.close() })
}