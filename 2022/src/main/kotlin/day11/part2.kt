package com.gngsn.year2022.day11

import com.gngsn.year2022.readFile

fun main() {
    val monkeys = mutableListOf<Monkey>()
    var lmc = 1L

    readFile("src/main/kotlin/day11/input.txt")
        .buffer(7)
        .doOnNext { lines ->
            val monkey = newMonkey(lines)
            monkeys.add(monkey)
            lmc *= monkey.divide!!
        }.subscribe()

    playNTimes(monkeys, lmc, 10000)
    printAnswer(monkeys)
}

private fun playNTimes(monkeys: List<Monkey>, lmc: Long, n: Int) {
    (1..n).forEach { _ -> play(monkeys, lmc) }
}

private fun play(monkeys: List<Monkey>, lmc: Long) {

    monkeys.forEach { m ->
        while (!m.items.isEmpty()) {
            val item = m.inspectAndReturn()
            val value = divideValue(m.op!!, item) % lmc

            if (value % m.divide!! == 0L) monkeys[m.trueTo!!].addItem(value)
            else monkeys[m.falseTo!!].addItem(value)
        }
    }
}
