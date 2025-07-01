package com.example
object DivideTriangles {
    private fun divideTriangle (triangle: Triangle) : Triangle{
        val divide = fun(point1: Point2D, point2: Point2D): Point2D = Point2D((point1.x + point2.x)/2, (point1.y + point2.y)/2)
        return Triangle(divide(triangle.A,triangle.B), divide(triangle.B,triangle.C), divide(triangle.C,triangle.A))
    }
    private fun addMissingTriangles(triangle: Triangle) : MutableSet<Triangle>{
        val newTriangle = divideTriangle(triangle)
        val newTriangleLeft = Triangle(newTriangle.A, triangle.B, newTriangle.B)
        val newTriangleRight = Triangle(newTriangle.C, newTriangle.B, triangle.C)
        val newTriangleUpper = Triangle(triangle.A, newTriangle.A, newTriangle.C)
        return mutableSetOf(newTriangle, newTriangleLeft, newTriangleRight, newTriangleUpper)
    }
    fun divideTriangles(triangles: MutableSet<Triangle>, count : Int = numTriangles) : MutableSet<Triangle> {
        if (triangles.size >= count)
            return triangles
        val newTriangles = mutableSetOf<Triangle>()
        for (triangle in triangles)
            newTriangles.addAll(addMissingTriangles(triangle))
        return divideTriangles(newTriangles, count)
    }
}