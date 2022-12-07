package com.gngsn.year2022.day7

import com.gngsn.year2022.readFile

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
                if ("cd" == line[1]) {

                    pwd = if (line[2] == "..") {
                        pwd?.parent
                    } else {
                        pwd?.children?.first { c -> c.name == line[2] } as Directory?
                    }
                }

                return@doOnNext
            }

            val child = if (line[0] == "dir") Directory(line[1], pwd) else File(line[1], pwd, line[0].toLong())
            pwd?.addChild(child)

        }.subscribe()

    println(root?.print())
    println("TOTAL: ${root?.sumAtMost()}")
}

/* OUTPUT

Directory(name=/, size=48381165)
  - Directory(name=a, size=94853)
	  - Directory(name=e, size=584)
		  - File(name=i, size=584)
	  - File(name=f, size=29116)
	  - File(name=g, size=2557)
	  - File(name=h.lst, size=62596)
  - File(name=b.txt, size=14848514)
  - File(name=c.dat, size=8504156)
  - Directory(name=d, size=24933642)
	  - File(name=j, size=4060174)
	  - File(name=d.log, size=8033020)
	  - File(name=d.ext, size=5626152)
	  - File(name=k, size=7214296)

TOTAL: 95437

 */

private fun String.isCommand() = this == "$"

class Directory(
    override val name: String,
    parent: Directory?,
    var children: MutableList<File> = mutableListOf()
) : File(name, parent) {

    fun addChild(file: File) {
        this.children.add(file)
    }


    fun sumAtMost(atMost: Int = 100000): Long {
        return children.filterIsInstance<Directory>().map { ch ->
            val sz = ch.getFileSize()
            ch.sumAtMost() + if (sz < atMost) sz else 0
        }.sum()
    }

    override fun print(dept: Int): String {
        var structure = "$this\n"
        children.forEach { ch -> structure += "\t".repeat(dept) + "  - " + ch.print(dept + 1) }

        return structure
    }


    override fun getFileSize(): Long {
        var dirSize: Long = 0
        this.children.forEach { c -> dirSize += c.getFileSize() }

        return dirSize
    }

    override fun toString(): String {
        return "Directory(name=$name, size=${this.getFileSize()})"
    }
}

open class File(
    open val name: String,
    val parent: Directory?,
    var size: Long = 0
) {

    open fun print(dept: Int = 0): String {
        return "$this\n"
    }

    override fun toString(): String {
        return "File(name=$name, size=${this.getFileSize()})"
    }

    open fun getFileSize(): Long = this.size
}


