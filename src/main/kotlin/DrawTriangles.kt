package com.example

object DrawTriangles {
    fun drawCanvas(w: Int, h: Int): String {
        val wstr = w.toString()
        val hstr = h.toString()
        val html = """
       <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <title>Triangle</title>
        </head>
        <body>
            <canvas id="canvas" width="$wstr" height="$hstr" style="border:1px solid black;"></canvas>
            <script>
              const canvas = document.getElementById('canvas');
              const ctx = canvas.getContext('2d');
            </script>
        </body>
        </html>
    """.trimIndent()
        return html
    }

    fun drawTriangle(triangle: Triangle) : String {
        val insertBefore = "</script>"
        val html = """
        ctx.beginPath();
        ctx.moveTo(${triangle.A.x}, ${triangle.A.y});
        ctx.lineTo(${triangle.B.x}, ${triangle.B.y});
        ctx.lineTo(${triangle.C.x}, ${triangle.C.y});
        ctx.closePath();
        ctx.strokeStyle = "blue";
        ctx.lineWidth = 2;
        ctx.stroke();
    """.trimIndent()
        return html
        }
    fun drawTriangles(triangles: MutableSet<Triangle>): String {
        val baseHtml = drawCanvas(width, height)
        val insertBefore = "</script>"
        val triangleJs = triangles.joinToString("\n") { drawTriangle(it) }
        val finalHtml = baseHtml.replace(insertBefore, "$triangleJs\n$insertBefore")
        return finalHtml
    }
    }