package com.gngsn.year2022.day8

import com.gngsn.year2022.readFile


fun main() {
    val grid = readFile("src/main/kotlin/day8/input.txt").collectList().block() as List<String>

    var y = grid.size - 2
    var count = 0

    while (y > 0) {
        if (y == grid.size) continue
        var x = grid[0].length - 2

        while (x > 0) {
            if (x == grid[y].length) continue
            count += if (isVisible(grid, x--, y)) 1 else 0
        }
        y--
    }

    println("SIZE: ${grid.size * grid[0].length} | COUNT: ${(grid.size * grid[0].length) - count}")
}

fun isVisible(grid: List<String>, x: Int, y: Int): Boolean =
    isVisibleRow(grid, y, x, 1) && isVisibleRow(grid, y, x, -1) &&
            isVisibleCol(grid, x, y, 1) && isVisibleCol(grid, x, y, -1)

fun isVisibleRow(grid: List<String>, y: Int, x: Int, add: Int, dept: Int = 1): Boolean {
    val cx = x + (add * dept)
    if (cx < 0 || cx == grid[y].length) return false
    if (grid[y][x] <= grid[y][cx]) return true
    return isVisibleRow(grid, y, x, add, dept + 1)
}

fun isVisibleCol(grid: List<String>, x: Int, y: Int, add: Int, dept: Int = 1): Boolean {
    val cy = y + (add * dept)
    if (cy < 0 || cy == grid.size) return false
    if (grid[y][x] <= grid[cy][x]) return true
    return isVisibleCol(grid, x, y, add, dept + 1)
}


