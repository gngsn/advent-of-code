package com.gngsn.twentythree.day2

import com.gngsn.twentythree.readFile

//  Game 47: 6 red, 6 blue; 14 blue, 7 green, 2 red; 8 blue, 3 red
//    12 red , 13 green , and 14 blue
enum class Color {
    red, blue, green
}

fun main() {
    readFile("day2/input.txt")
        .map { game ->
            val m = mutableMapOf<String, List<Int>>(
                Color.red.name to mutableListOf(),
                Color.green.name to mutableListOf(),
                Color.blue.name to mutableListOf()
            )

            game.split(':').let { split ->
                split[1].split(';').forEach { c ->
                    c.split(',').forEach {
                        it.trim().split(' ').let { s ->
                            m[s[1]] = m[s[1]]!! + s[0].toInt()
                        }
                    }
                }

                Cube(
                    id = split[0].split(' ')[1].toInt(),
                    red = m[Color.red.name]!!,
                    green = m[Color.green.name]!!,
                    blue = m[Color.blue.name]!!,
                )
            }
        }
        .filter { it.canBeConsidered(12, 13, 14) }
        .sumOf { it.id }
        .let { println(it) }
}

class Cube(
    val id: Int,
    val red: List<Int>,
    val green: List<Int>,
    val blue: List<Int>
) {
    fun canBeConsidered(red: Int, green: Int, blue: Int) =
        this.red.max() <= red && this.green.max() <= green && this.blue.max() <= blue

}