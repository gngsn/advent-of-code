package com.gngsn.year2022.day8

import com.gngsn.year2022.readFile

fun main() {
    val grid = readFile("src/main/kotlin/day8/input.txt").collectList().block() as List<String>

    var y = grid.size - 2
    var max = 0

    while (y > 0) {
        if (y == grid.size) continue
        var x = grid[0].length - 2

        while (x > 0) {
            if (x == grid[y].length) continue
            max = max.coerceAtLeast(highestScenicScore(grid, x--, y))
        }
        y--
    }

    println("SIZE: ${grid.size * grid[0].length} | COUNT: $max")
}

fun highestScenicScore(grid: List<String>, x: Int, y: Int): Int {
    return scoreRow(grid, y, x, 1) * scoreRow(grid, y, x, -1) *
            scoreCol(grid, x, y, 1) * scoreCol(grid, x, y, -1)
}

fun scoreRow(grid: List<String>, y: Int, x: Int, add: Int, dept: Int = 1): Int {
    val cx = x + (add * dept)
    if (cx < 0 || cx == grid[y].length) return dept - 1
    if (grid[y][x] <= grid[y][cx]) return dept
    return scoreRow(grid, y, x, add, dept + 1)
}

fun scoreCol(grid: List<String>, x: Int, y: Int, add: Int, dept: Int = 1): Int {
    val cy = y + (add * dept)
    if (cy < 0 || cy == grid.size) return dept - 1
    if (grid[y][x] <= grid[cy][x]) return dept
    return scoreCol(grid, x, y, add, dept + 1)
}

