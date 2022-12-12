package com.gngsn.year2022.day9

import com.gngsn.year2022.readFile
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