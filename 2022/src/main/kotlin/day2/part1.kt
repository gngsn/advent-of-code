package com.gngsn.year2022.day2

import com.gngsn.year2022.readFile
import java.util.concurrent.atomic.AtomicInteger

/*   A  B  C
  X [3, 0, 6]
  Y [6, 3, 0]
  Z [0, 6, 3]
*/
fun main(args: Array<String>) {
    val match = arrayOf(arrayOf(3, 6, 0), arrayOf(0, 3, 6), arrayOf(6, 0, 3))
    val total = AtomicInteger(0)

    readFile("src/main/kotlin/day2/input.txt")
        .doOnNext { line ->
            val list = line.split(" ")
            val opp = list[0][0] - 'A'
            val elf = list[1][0] - 'X'
            total.getAndAccumulate(match[opp][elf] + (elf + 1)) {x, y -> x + y}
            println("opp: ${opp} VS elf: ${elf} => ${match[opp][elf] + (elf + 1)}")
        }.subscribe()

    println("TOTAL: " + total.get())
}