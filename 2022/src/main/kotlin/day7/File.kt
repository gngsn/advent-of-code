package com.gngsn.year2022.day7

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
