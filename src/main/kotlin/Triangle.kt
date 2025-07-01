package com.example
data class Point2D(val x: Int, val y: Int) {
    fun printPoint(){
        println("x: $x, y: $y")
    }
}

data class Triangle(val A: Point2D, val B: Point2D, val C: Point2D) {
    fun printTriangle(){
        println("A:  x: ${A.x} y: ${A.y}")
        println("B:  x: ${B.x} y: ${B.y}")
        println("C:  x: ${C.x} y: ${C.y}")
    }
}

val triangleABC = Triangle(Point2D(width/2,0), Point2D(0, height), Point2D(width, height))