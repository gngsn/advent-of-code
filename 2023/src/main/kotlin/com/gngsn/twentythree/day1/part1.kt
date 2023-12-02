package com.gngsn.twentythree.day1

import com.gngsn.twentythree.getFile

fun main() {

    getFile("day1/input.txt").absoluteFile
        .inputStream()
        .reader()
        .use { it.readText() }
        .let { println(it) }
}