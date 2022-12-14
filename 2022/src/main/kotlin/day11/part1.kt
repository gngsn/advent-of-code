package com.gngsn.year2022.day11

import com.gngsn.year2022.readFile
import reactor.core.publisher.Flux
import java.util.*


fun main() {
    val monkeys = mutableListOf<Monkey>()

    readFile("src/main/kotlin/day11/input.txt")
        .buffer(7).doOnNext { lines ->
            var monkey: Monkey? = null
            lines.map { it.split(":") }.forEach { line ->
                with(line[0]) {
                    when {
                        contains("Monkey")         -> monkey = Monkey()
                        contains("Starting items") -> line[1].split(",").map { i -> i.trim().toInt() }.forEach { i -> monkey?.addItem(i) }
                        contains("Operation")      -> monkey?.op = line[1].substringAfter("=").trim()
                        contains("Test")           -> monkey?.divide = line[1].substringAfterLast(" ").trim().toInt()
                        contains("If true")        -> monkey?.trueTo = line[1][line[1].length - 1].digitToInt()
                        contains("If false")       -> {
                            monkey?.falseTo = line[1][line[1].length - 1].digitToInt();
                            monkeys.add(monkey!!)
                        }
                        isBlank() -> {}
                        else -> throw RuntimeException("Not supported command '${line[0]}'")
                    }
                }
            }
        }.subscribe()

    (1..20).forEach { play(monkeys) }

    Flux.fromIterable(monkeys)
        .sort(MonkeyComparator())
        .take(2).map { it.total }
        .reduce {t,u -> t*u}
        .subscribe { println("ANSWER: $it") }
}

fun play(monkeys: MutableList<Monkey>) {
    monkeys.forEach { m ->
        while(!m.items.isEmpty()) {
            val item = m.inspectAndReturn()
            val value = operate(m.op!!, item) / 3
//            println(" - item: $item, value: $value,  divided? ${if (value % m.divide!! == 0) m.trueTo!! else m.falseTo!!}")
            if (value % m.divide!! == 0) monkeys[m.trueTo!!].addItem(value)
            else monkeys[m.falseTo!!].addItem(value)
        }
    }
//    println("\nmonkeys: "); monkeys.forEach { println("$it") }
}

fun operate(operate: String, num: Int): Int {
    val op = operate.split(" ").toMutableList()
    val a = if (op[0] == "old") num else op[0].toInt()
    val b = if (op[2] == "old") num else op[2].toInt()

    return with(op[1]) {
        when {
            equals("+") -> a + b
            equals("-") -> a - b
            equals("*") -> a * b
            else -> throw RuntimeException("Not supported command '${op[1]}'")
        }
    }
}

class Monkey(
    var items: LinkedList<Int> = LinkedList(),
    var op: String? = null,
    var divide: Int? = null,
    var trueTo: Int? = null,
    var falseTo: Int? = null,
) {
    var total = 0

    fun inspectAndReturn(): Int {
        total++
        return items.poll()
    }

    fun addItem(item: Int) {
        this.items.add(item)
    }

    override fun toString(): String {
        return "Monkey(items=$items, op=$op, divide=$divide, monkeyTrue=$trueTo, monkeyFalse=$falseTo, total=$total)"
    }
}

class MonkeyComparator: Comparator<Monkey>{
    override fun compare(o1: Monkey?, o2: Monkey?): Int {
        if(o1 == null || o2 == null) return 0;
        return o2.total.compareTo(o1.total)
    }
}
