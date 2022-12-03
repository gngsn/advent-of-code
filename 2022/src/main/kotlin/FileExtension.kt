package com.gngsn.year2022

import reactor.core.publisher.Flux
import java.nio.file.Files
import java.nio.file.Paths

fun readFile(fileName: String): Flux<String> {
    val ipPath = Paths.get(fileName)

    return Flux.using(
        { Files.lines(ipPath) },
        { s -> Flux.fromStream(s) },
        { obj -> obj.close() })
}