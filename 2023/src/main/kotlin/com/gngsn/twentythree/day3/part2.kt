package com.gngsn.twentythree.day3

import com.gngsn.twentythree.readFile


fun main() {
    readFile("day3/input.txt")
        .let { board ->
            var sum = 0

            val mark = Array(board.size) { BooleanArray(board[0].length) }
            board.forEachIndexed { y, row ->
                row.forEachIndexed { x, c ->
                    if (c == '*') {
                        val nums: MutableList<Int> = mutableListOf()
                        
                        points.forEach { (j, i) ->
                            val ny = y + j
                            val nx = x + i

                            if (boundary(mark, ny, nx) && board[ny][nx].isDigit()) {
                                mark[ny][nx] = true
                                nums += getNum(board, ny, nx, mark)
                            }
                        }

                        if (nums.size == 2) {
                            sum += nums[0] * nums[1]
                        }
                    }
                }
            }

            sum
        }
        .let { println(it) }
}

private fun boundary(square: Array<BooleanArray>, y: Int, x: Int): Boolean {
    return y >= 0 && y < square.size && x >= 0 && x < square[y].size && !square[y][x]
}

private fun getNum(square: List<String>, y: Int, x: Int, mark: Array<BooleanArray>): Int {
    var numstr = "${square[y][x]}"
    mark[y][x] = true

    var left = x
    while (--left >= 0 && square[y][left].isDigit()) {
        numstr = square[y][left].toString() + numstr
        mark[y][left] = true
    }

    var right = x
    while (++right < square[y].length && square[y][right].isDigit()) {
        numstr += square[y][right].toString()
        mark[y][right] = true
    }

    return numstr.toInt()
}