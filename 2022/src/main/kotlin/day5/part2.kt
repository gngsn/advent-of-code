package com.gngsn.year2022.day5

import com.gngsn.year2022.readFile
import java.util.concurrent.atomic.AtomicBoolean

fun main(args: Array<String>) {
    val isSort = AtomicBoolean(true)
    val queueMap = mutableMapOf<Int, String>()

    readFile("src/main/kotlin/day5/input.txt")
        .doOnNext { line ->
            if (line.isBlank() || line[1] == '1') {
                isSort.set(false)
                return@doOnNext
            }

            if (isSort.acquire) {
                var i = 0
                while (line.length > i) {
                    val st = line.slice(i + 1 until i + 2)

                    if (st.isNotBlank()) queueMap[i / 4] = queueMap.getOrDefault(i / 4, "").plus(st)
                    i += 4
                }
                return@doOnNext
            }

            val cmd = line.split(" ")
            val num = cmd[1].toInt()
            val from = cmd[3].toInt() - 1
            val to = cmd[5].toInt() - 1

            val move: String? = queueMap[from]?.slice(0 until num)

            queueMap.put(from, queueMap[from]!!.substring(num))
            queueMap[to] = move!!.plus(queueMap[to]!!)

        }.subscribe()

    queueMap.toSortedMap().forEach { (_, v) -> print(v.getOrNull(0)) }
}