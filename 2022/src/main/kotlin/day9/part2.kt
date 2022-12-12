package com.gngsn.year2022.day9

import com.gngsn.year2022.readFile
import kotlin.math.absoluteValue
import kotlin.math.sign


fun main() {
    val rope = Array(10) { Pos() }
    val tailVisits = mutableSetOf(Pos())

    val line = readFile("src/main/kotlin/day9/input.txt").map { s ->
        s.substringBefore(" ").repeat(s.substringAfter(' ').toInt())
    }.toIterable().joinToString("")

    line.forEach { direction ->
        rope[0] = rope[0].move(direction)
        rope.indices.windowed(2, 1) { (head, tail) ->
            if (!rope[head].touches(rope[tail])) {
                rope[tail] = rope[tail].moveTowards(rope[head])
            }
        }
        tailVisits += rope.last()
    }

    println("VISIT: ${tailVisits.size}")
}

private data class Pos(val x: Int = 0, val y: Int = 0) {
    fun move(direction: Char): Pos =
        when (direction) {
            'U' -> copy(y = y - 1)
            'D' -> copy(y = y + 1)
            'L' -> copy(x = x - 1)
            'R' -> copy(x = x + 1)
            else -> throw IllegalArgumentException("Unknown Direction: $direction")
        }

    fun moveTowards(other: Pos): Pos =
        Pos(
            (other.x - x).sign + x,
            (other.y - y).sign + y
        )

    fun touches(other: Pos): Boolean =
        (x - other.x).absoluteValue <= 1 && (y - other.y).absoluteValue <= 1
}