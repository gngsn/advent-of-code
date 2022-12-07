package com.gngsn.year2022.day7

import com.gngsn.year2022.readFile

fun main(args: Array<String>) {
    var root: File? = null
    var pwd: File? = null

    readFile("src/main/kotlin/day7/input.txt")
        .doOnNext { raw ->
            val line = raw.split(" ")

            if (pwd == null) {
                root = File(line[2], null)
                pwd = root
                return@doOnNext
            }

            println("line: $line | curFile: $pwd")

            if (line[0].isCommand()) {
                if ("cd" == line[1]) {

                    if (line[2] == "..") {
                        pwd = pwd?.parent
                    } else {
                        println("CD CMD - curFile : $pwd")
                        pwd = pwd?.children?.first { c -> c.name == line[2] }
                    }
                }

                return@doOnNext
            }

            val child = if (line[0] == "dir") File(line[1], pwd) else File(line[1], pwd, line[0].toLong())
            pwd?.addChild(child)

        }.subscribe  { line ->
            println("\n[line: $line]\n${root?.print()}\n\n")
        }
}

private fun String.isCommand() = this == "$"

data class File(
    val name: String,
    val parent: File?,
    var size: Long = 0,
    var children: MutableList<File>? = null
) {
    fun addChild(file: File) {
        if (this.children.isNullOrEmpty()) this.children = mutableListOf()
        this.children!!.add(file)
    }

    fun getFileSize(): Long {
        if (children.isNullOrEmpty()) return this.size
        var dirSize: Long = 0
        children!!.forEach() { c -> dirSize += c.size }

        return dirSize
    }

    override fun toString(): String {
        return "File(name=$name, size=${this.getFileSize()})"
    }

    fun print(dept: Int = 0): String {
        var structure = "File(name=$name, size=${this.getFileSize()})\n"

        if (!children.isNullOrEmpty()) {
            children?.forEach() { ch -> structure += "\t".repeat(dept) + "  - " + ch.print(dept+1) }
        }

        return structure
    }
}

