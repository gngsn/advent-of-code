package com.gngsn.year2022.day10

import com.gngsn.year2022.readFile

enum class Instr(val code: String) {
    ADDX("addx"), NOOP("noop");
}

fun main() {
    val targetCycle = listOf(20, 60, 100, 140, 180, 220)
    var targetCycleIdx = 0
    var cycle = 1
    var sign = 1
    var total = 0

    readFile("src/main/kotlin/day10/input.txt")
        .doOnNext { raw ->
            var afterSignal = 0

            when(raw.substringBefore(" ")) {
                Instr.ADDX.code -> {
                    cycle += 2
                    afterSignal = raw.substringAfter(" ").toInt()
                }
                Instr.NOOP.code -> cycle += 1
                else -> throw IllegalArgumentException("not supported instruction.")
            }

            if (cycle > targetCycle[targetCycleIdx]) total += targetCycle[targetCycleIdx++] * sign
            sign += afterSignal

        }.takeUntil{ targetCycle.size == targetCycleIdx }
        .subscribe()

    println("TOTAL: $total")
}
