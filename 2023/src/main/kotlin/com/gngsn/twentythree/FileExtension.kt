package com.gngsn.twentythree

import java.io.File

fun readFile(fileName: String): List<String> =
    File("2023/src/main/kotlin/com/gngsn/twentythree/$fileName")
        .inputStream()
        .reader()
        .use { it.readLines() }
