package com.gngsn.twentythree.day3

import com.gngsn.twentythree.readFile

data class Pair(
    val y: Int,
    val x: Int,
)

val points: List<Pair> = listOf(
    Pair(-1, -1),
    Pair(-1, 0),
    Pair(-1, 1),
    Pair(1, 1),
    Pair(1, 0),
    Pair(1, -1),
    Pair(0, -1),
    Pair(0, 1),
)

fun main() {

    readFile("day3/input.txt")
        .let { board ->
            var sum = 0

            val mark = Array(board.size) { BooleanArray(board[0].length) }
            board.forEachIndexed { y, row ->
                row.forEachIndexed { x, c ->
                    mark[y][x] = (!c.isDigit() && c != '.')
                }
            }

            var y = -1
            while (++y < board.size) {
                var x = -1
                while (++x < board[y].length) {
                    var next = x
                    while (next < board[y].length && board[y][next].isDigit()) {
                        next += 1
                    }

                    var num = ""
                    var flag = false
                    while (next != x) {
                        points.forEach { (j, i) ->
                            flag = flag || boundary(mark, y + j, x + i)
                        }
                        num += board[y][x++]
                    }

                    if (flag) {
                        sum += num.toInt()
                    }
                }
            }

            sum
        }
        .let { println(it) }
}


fun boundary(square: Array<BooleanArray>, y: Int, x: Int): Boolean {
    return y >= 0 && y < square.size && x >= 0 && x < square[y].size && square[y][x]
}
