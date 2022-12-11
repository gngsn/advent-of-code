package com.gngsn.year2022.day9

import com.gngsn.year2022.readFile
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt


fun main() {
    val trace = mutableSetOf<Pair<Int, Int>>()
    val start: Pair<Int, Int> = Pair(0, 0)
    var head = start
    var tail = start

    readFile("src/main/kotlin/day9/input.txt")
        .doOnNext { line ->

            val cmd = line.split(" ")
            val dir = cmd[0]
            var mv = cmd[1].toInt()

            while(mv-- > 0) {

                head = move(head, dir)
                val distance = sqrt((head.first - tail.first).toDouble().pow(2) +
                        (head.second - tail.second).toDouble().pow(2))

                if (distance > 2) {
                    tail = when (dir) {
                        "U" -> Pair(head.first, head.second-1)
                        "D" -> Pair(head.first, head.second+1)
                        "R" -> Pair(head.first-1, head.second)
                        "L" -> Pair(head.first+1, head.second)
                        else -> throw IllegalArgumentException("Not supported command")
                    }
                } else if (distance == 2.0) {
                    tail = move(tail, dir)
                }

                println("{head=$head, tail =$tail}")
                trace.add(tail)
            }
            println("{trace=$trace}")
        }.subscribe()

    println("VISIT: ${trace.size}")
}

private fun move(pos: Pair<Int, Int>, dir: String): Pair<Int, Int> =
     when (dir) {
        "U" -> Pair(pos.first, pos.second + 1)
        "D" -> Pair(pos.first, pos.second - 1)
        "R" -> Pair(pos.first + 1, pos.second)
        "L" -> Pair(pos.first - 1, pos.second)
        else -> throw IllegalArgumentException("Not supported command")
    }


fun move2(from: Pair<Int, Int>, to: Pair<Int, Int>): MutableSet<Pair<Int, Int>> {
    val track = mutableSetOf<Pair<Int, Int>>()
    println("move from{x=${from.first}, y=${from.second}}, to{x=${to.first}, y=${to.second}}")
    val xweight = if (to.first < 0) -1 else 1
    val yweight = if (to.second < 0) -1 else 1

    (0 until abs(from.first - to.first)).forEach { i -> track.add(Pair(from.first + (i * xweight) + 1, from.second)) }
    (0 until abs(from.second - to.second)).forEach { i -> track.add(Pair(from.first, from.second + (i * yweight) + 1)) }

    return track
}