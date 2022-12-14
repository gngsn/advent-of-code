package com.gngsn.year2022.day10

import com.gngsn.year2022.readFile
import kotlin.math.abs


fun main() {
    val graph = MutableList(6) { MutableList(40) { '-' } }
    var cycle = 0
    var sign = 1

    readFile("src/main/kotlin/day10/input.txt")
        .doOnNext { raw ->
            handleSign(graph, cycle, sign)

            when(raw.substringBefore(" ")) {
                Instr.ADDX.code -> {
                    cycle += 2
                    handleSign(graph, cycle-1, sign)
                    sign += raw.substringAfter(" ").toInt()
                }
                Instr.NOOP.code -> cycle += 1
                else -> throw IllegalArgumentException("$raw not supported instruction.")
            }
        }.takeUntil{ cycle >= 240 }
        .subscribe()

    println(graph.joinToString("\n") { it.joinToString("") })
}

fun handleSign(graph: List<MutableList<Char>>, cycle: Int, sign: Int) {
    graph[cycle/40][cycle%40] = if (abs(sign-(cycle % 40)) <= 1) '#' else '.'
}