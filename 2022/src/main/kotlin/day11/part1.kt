package com.gngsn.year2022.day11

import com.gngsn.year2022.readFile
import reactor.core.publisher.Flux

fun main() {
    val monkeys = mutableListOf<Monkey>()

    readFile("src/main/kotlin/day11/input.txt")
        .buffer(7)
        .doOnNext { lines -> monkeys.add(newMonkey(lines)) }
        .subscribe()

    playNTimes(monkeys, 20)
    printAnswer(monkeys)
}

fun printAnswer(monkeys: List<Monkey>) {
    Flux.fromIterable(monkeys)
        .sort(Monkey.MonkeyComparator())
        .take(2).map { it.total }
        .reduce { t, u -> t * u }
        .subscribe { println("ANSWER: $it") }
}

fun newMonkey(lines: List<String>): Monkey {
    var monkey: Monkey? = null

    lines.map { it.split(":") }.forEach { line ->
        with(line[0]) {
            when {
                contains("Monkey") -> monkey = Monkey()
                contains("Starting items") -> line[1].split(",").map { i -> i.trim().toLong() }.forEach { i -> monkey?.addItem(i) }
                contains("Operation") -> monkey?.op = line[1].substringAfter("=").trim()
                contains("Test") -> monkey?.divide = line[1].substringAfterLast(" ").trim().toLong()
                contains("If true") -> monkey?.trueTo = line[1][line[1].length - 1].digitToInt()
                contains("If false") -> {
                    monkey?.falseTo = line[1][line[1].length - 1].digitToInt()
//                    monkeys.add(monkey!!)
                }
                isBlank() -> {}
                else -> throw RuntimeException("Not supported command '${line[0]}'")
            }

        }
    }
    return monkey!!
}

fun divideValue(operate: String, num: Long): Long {
    val op = operate.split(" ").toMutableList()
    val a = if (op[0] == "old") num else op[0].toLong()
    val b = if (op[2] == "old") num else op[2].toLong()

    return with(op[1]) {
        when {
            equals("+") -> (a + b)
            equals("-") -> (a - b)
            equals("*") -> a * b
            else -> throw RuntimeException("Not supported command '${op[1]}'")
        }
    }
}

private fun playNTimes(monkeys: List<Monkey>, n: Int) {
    (1..n).forEach { _ -> play(monkeys) }
}

private fun play(monkeys: List<Monkey>) {
    monkeys.forEach { m ->
        while(!m.items.isEmpty()) {
            val item = m.inspectAndReturn()
            val value = divideValue(m.op!!, item) / 3L
            if (value % m.divide!! == 0L) monkeys[m.trueTo!!].addItem(value)
            else monkeys[m.falseTo!!].addItem(value)
        }
    }
}
