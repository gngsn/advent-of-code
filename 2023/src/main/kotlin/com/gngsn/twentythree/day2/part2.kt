package com.gngsn.twentythree.day2

import com.gngsn.twentythree.day2.components.Color
import com.gngsn.twentythree.day2.components.Cube
import com.gngsn.twentythree.readFile

fun main() {
    readFile("day2/input.txt")
        .map { record ->
            record.split(':').let { games ->
                games[1].split(';').flatMap { game ->
                    game.split(',').map { colors ->
                        colors.trim().split(' ')
                            .let { it[1] to it[0].toInt() }
                    }
                }.fold<Pair<String, Int>, MutableMap<String, List<Int>>>(mutableMapOf()) { p, n ->
                    p[n.first] = p.getOrDefault(n.first, mutableListOf()) + n.second; p
                }.let {
                    Cube(
                        id = games[0].split(' ')[1].toInt(),
                        red = it[Color.red.name]!!,
                        green = it[Color.green.name]!!,
                        blue = it[Color.blue.name]!!,
                    )
                }
            }
        }
        .sumOf { it.multiplyMinimums() }
        .let { println(it) }
}