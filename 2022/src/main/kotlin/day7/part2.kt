package com.gngsn.year2022.day7

import com.gngsn.year2022.day7.Constants.CD
import com.gngsn.year2022.day7.Constants.DIR
import com.gngsn.year2022.day7.Constants.PARENT_DIR
import com.gngsn.year2022.readFile
import reactor.core.publisher.Mono
import kotlin.math.min

object Constants {
    const val CMD = "$"
    const val CD = "cd"
    const val DIR = "dir"
    const val PARENT_DIR = ".."
}

fun main(args: Array<String>) {
    var root: Directory? = null
    var pwd: Directory? = null

    readFile("src/main/kotlin/day7/input.txt")
        .doOnNext { raw ->
            val line = raw.split(" ")

            if (pwd == null) {
                root = Directory(line[2], null)
                pwd = root
                return@doOnNext
            }

            if (line[0].isCommand()) {

                if (CD == line[1]) {
                    val dest = line[2]

                    pwd = if (dest == PARENT_DIR) pwd?.parent
                          else pwd?.children?.first { c -> c.name == dest } as Directory?

                }
                return@doOnNext
            }

            val dirOrFile = if (line[0] == DIR) Directory(line[1], pwd)
                            else File(line[1], pwd, line[0].toLong())

            pwd?.addChild(dirOrFile)

        }.subscribe()

    Mono.just(DirTraversal(root!!.getFileSize()))
        .doOnNext { println(root!!.print()) }
        .doOnNext { t -> t.sumAtMost(root!!) }
        .subscribe { t -> println("TOTAL: ${t.min}") }
}

private fun String.isCommand() = this == "$"

class DirTraversal(val rootSize: Long,
                   var min: Long = Long.MAX_VALUE,
                   private val atMost: Long = 40000000) {

    fun sumAtMost(dir: Directory) {
        return dir.children.filterIsInstance<Directory>().forEach { ch ->
            Mono.just(ch.getFileSize())
                .doOnNext { sumAtMost(ch) }
                .subscribe { size -> if (rootSize - size < atMost) min = min(min, size)}
        }
    }
}


