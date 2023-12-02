package com.gngsn.twentythree

import java.io.File

fun getFile(fileName: String): File =
    File("2023/src/main/kotlin/com/gngsn/twentythree/$fileName")

fun readFile(fileName: String): List<String> =
    File("2023/src/main/kotlin/com/gngsn/twentythree/$fileName")
        .inputStream()
        .reader()
        .use { it.readLines() }
