package com.gngsn.twentythree.day5

import com.gngsn.twentythree.readFile

fun main() {
    readFile("day5/input.txt")
        .let { almanac ->
            val relations: MutableList<Relation> = mutableListOf()

            var i = -1
            val rows = almanac.drop(2)
            while (++i < rows.size) {
                if (rows[i].isBlank() || rows[i][0].isDigit()) {
                    continue
                }

                val name = rows[i].split(' ')[0].split("-to-")

                val lines: MutableList<Data> = mutableListOf()
                while (++i < rows.size && rows[i].isNotBlank() && rows[i][0].isDigit()) {
                    lines.add(Data.of(rows[i]))
                }

                val relation = Relation(name[0], name[1], lines)
                relations.add(relation)
            }

            val set = Set(relations)

            val seeds = splitSpacing(almanac[0].split(':')[1])
            seeds.map {
                set.next("seed", it)
            }
        }
        .min()
        .let { println(it) }
}

class Set(
    private val relations: List<Relation>
) {
    fun next(f: String, num: Long): Long =
        relations.find { it.from == f }?.let {
            return next(it.to, it.find(num))
        } ?: num
}

class Relation(
    val from: String,
    val to: String,
    val data: List<Data>,
) {
    fun find(num: Long): Long = data.find { it.match(num) }?.get(num) ?: num
}

class Data(
    val dest: Long,
    val source: Long,
    val range: Long,
) {
    fun match(num: Long): Boolean = num >= source && num <= source + range

    fun get(num: Long): Long = dest + (num - source)

    companion object {
        fun of(line: String): Data {
            line.split(' ').let {
                return Data(
                    dest = it[0].toLong(),
                    source = it[1].toLong(),
                    range = it[2].toLong(),
                )
            }
        }
    }
}

private fun splitSpacing(it: String): List<Long> =
    it.split(' ').filter { i -> i != "" }.map { n -> n.toLong() }
