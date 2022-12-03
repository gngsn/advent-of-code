package day1

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class day1 {
}

fun main(args: Array<String>) {
    val inputs: List<String> = readFile()
}

fun readFile(): List<String> {
    val file = File("./input.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    reader.lines().forEach { println(it) }
}