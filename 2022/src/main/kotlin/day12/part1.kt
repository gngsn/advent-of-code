package com.gngsn.year2022.day12.part1

import com.gngsn.year2022.readFile
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

val dx = listOf(-1, 0, 1, 0)
val dy = listOf(0, 1, 0, -1)

val count = AtomicInteger(1)
val queue = LinkedList<Point>()

fun main() {
    val square = readFile("src/main/kotlin/day12/input.txt").collectList().block()!!
    val dist: MutableList<MutableList<Int>> = MutableList(square.size) { MutableList(square[0].length) { 0 } }

    queue.add(Point(0, 0))

    while (queue.isNotEmpty()) {
        val next = queue.pop()
        val visited = MutableList(20) { MutableList(20) { false } }
        visit(square, next, visited)
        if (square[next.y][next.x] == 'E') break
    }
}


fun visit(square: List<String>, pos: Point, visited: MutableList<MutableList<Boolean>>) {
    if (visited[pos.y][pos.x]) return
    visited[pos.y][pos.x] = true
    if (square[pos.y][pos.x] == 'E') return

    if (ableVisitChar(pos, Point(pos.y, pos.x - 1), square)) {
        println("pos: $pos <")
//        visit(square, Point(pos.y, pos.x - 1), visited)
        queue.add(Point(pos.y, pos.x - 1))
    }

    if (ableVisitChar(pos, Point(pos.y, pos.x + 1), square)) {
        println("pos: $pos  >")
//        visit(square, Point(pos.y, pos.x + 1), visited)
        queue.add(Point(pos.y, pos.x + 1))
    }

    if (ableVisitChar(pos, Point(pos.y - 1, pos.x), square)) {
        println("pos: $pos  ^")
//        visit(square, Point(pos.y - 1, pos.x), visited)
        queue.add(Point(pos.y - 1, pos.x))
    }

    if (ableVisitChar(pos, Point(pos.y + 1, pos.x), square)) {
        println("pos: $pos  v")
//        visit(square, Point(pos.y + 1, pos.x), visited)
        queue.add(Point(pos.y + 1, pos.x))
    }
}

fun ableVisitChar(cur: Point, com: Point, square: List<String>): Boolean {
    if (com.x < 0 || com.x == square[0].length || com.y < 0 || com.y == square.size) return false
    println("cur: ${square[cur.y][cur.x]}, compare: ${square[com.y][com.x]}, result: ${square[cur.y][cur.x].code - 1 <= (square[com.y][com.x].code)}")
    return square[cur.y][cur.x].code - 1 <= (square[com.y][com.x].code)
}

//fun ableVisitChar(cur: Point, com: Point, square: List<String>, dist: MutableList<MutableList<Int>>): Int {
//    println("111")
//    if(square[cur.y][cur.x].code - 1 <= (square[com.y][com.x].code)) {
//        println("222")
//        return (if (square[com.y][com.x] < square[cur.y][cur.x]) dist[com.y][com.x] else dist[com.y][com.x]) + 1
//    }
//
//    println("333")
//    return 0;
//}
//fun ableVisitChar(cur: Point, com: Point, square: List<String>, visited: List<List<Boolean>>): Boolean {
//    if (com.x < 0 || com.x == square[0].length || com.y < 0 || com.y == square.size || visited[com.y][com.x]) return false
////    println("cur: ${square[cur.y][cur.x]}, compare: ${square[com.y][com.x]}, result: ${square[cur.y][cur.x] >= (square[com.y][com.x] - 1)}")
//    return square[cur.y][cur.x].code - 1 <= (square[com.y][com.x].code)
//}

typealias Point = Pair<Int, Int>

val Point.x get() = first
val Point.y get() = second
fun Point.toString(): String {
    return "Point{x=$x, y=$y}"
}