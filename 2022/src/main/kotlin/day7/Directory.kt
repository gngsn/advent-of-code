package com.gngsn.year2022.day7

class Directory(
    override val name: String,
    parent: Directory?,
    var children: MutableList<File> = mutableListOf()
) : File(name, parent) {

    fun addChild(file: File) {
        this.children.add(file)
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