package com.gngsn.aoc2023

import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream

fun readFile(fileName: String): Stream<String> {
    return Files.lines(Paths.get(fileName))
}