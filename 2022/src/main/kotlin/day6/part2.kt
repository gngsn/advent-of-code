package com.gngsn.year2022.day6

import com.gngsn.year2022.readFile


fun main(args: Array<String>) {
    readFile("src/main/kotlin/day6/input.txt")
        .subscribe { line ->
            println("answer: ${howManyMessageSkipped(line, 14)}")
        }
}