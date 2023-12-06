package com.gngsn.twentythree.day3.components

data class Point(
    val y: Int,
    val x: Int,
)


val points: List<Point> = listOf(
    Point(-1, -1),
    Point(-1, 0),
    Point(-1, 1),
    Point(0, -1),
    Point(1, 1),
    Point(1, 0),
    Point(1, -1),
    Point(0, 1),
)