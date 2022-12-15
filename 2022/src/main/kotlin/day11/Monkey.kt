package com.gngsn.year2022.day11

import java.util.*

class Monkey(
    var items: LinkedList<Long> = LinkedList(),
    var op: String? = null,
    var divide: Long? = null,
    var trueTo: Int? = null,
    var falseTo: Int? = null,
) {
    var total = 0L

    fun inspectAndReturn(): Long {
        total++
        return items.poll()
    }

    fun addItem(item: Long) {
        this.items.add(item)
    }

    override fun toString(): String {
        return "Monkey(items=$items, op=$op, divide=$divide, monkeyTrue=$trueTo, monkeyFalse=$falseTo, total=$total)"
    }

    class MonkeyComparator: Comparator<Monkey>{
        override fun compare(o1: Monkey?, o2: Monkey?): Int {
            if(o1 == null || o2 == null) return 0;
            return o2.total.compareTo(o1.total)
        }
    }
}