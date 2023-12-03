package com.gngsn.twentythree.day2.components

data class Cube(
    val id: Int,
    val red: List<Int>,
    val green: List<Int>,
    val blue: List<Int>
) {
    fun canBeConsidered(red: Int, green: Int, blue: Int) =
        this.red.max() <= red && this.green.max() <= green && this.blue.max() <= blue

    fun multiplyMinimums() =
        this.red.max() * this.green.max() * this.blue.max()
}