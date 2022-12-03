package com.gngsn.year2022.day2

import com.gngsn.year2022.readFile
import java.util.concurrent.atomic.AtomicInteger

/*   A  B  C
WIN [3, 1, 2]
DRW [1, 2, 3]
LOS [2, 3, 1]
*/
fun main(args: Array<String>) {
    val match = arrayOf(arrayOf(3, 1, 2), arrayOf(1, 2, 3), arrayOf(2, 3, 1))
    val winDrawLose: Map<String, Int> = mapOf("X" to 0, "Y" to 3, "Z" to 6) // X lose, Y draw, and Z win
    val total = AtomicInteger(0)

    readFile("src/main/kotlin/day2/input.txt")
        .doOnNext { line ->
            val list = line.split(" ")
            val opp = list[0][0] - 'A'
            val elf = list[1][0] - 'X'
            total.getAndAccumulate(match[opp][elf] + winDrawLose[list[1]]!!) {x, y -> x + y}
        }.subscribe()

    println("TOTAL: " + total.get())
}