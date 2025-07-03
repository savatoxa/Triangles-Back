package com.example
import io.ktor.server.application.*
import java.io.File
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.awt.Desktop
import java.net.URI
import kotlin.concurrent.thread

const val width = 1000
const val height = 800
const val numTriangles = 0
const val maxTriangles = 8

fun main(args: Array<String>) {
    thread {
        io.ktor.server.netty.EngineMain.main(args)
    }

    Thread.sleep(1000)

    if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().browse(URI("http://127.0.0.1:8080"))
    } else {
        println("open http://127.0.0.1:8080")
    }
}

fun Application.module() {
    routing {
        get("/") {
            call.respondText(
                """
                <form action="/draw" method="get">
                    <label>Dividion depth:
                      <input name="count" type="number" value="10">
                    </label>
                    <button type="submit">Calculate</button>
                </form>
                """.trimIndent(), ContentType.Text.Html
            )
        }

        get("/draw") {
            var count = call.request.queryParameters["count"]?.toIntOrNull() ?: 10
            if (count < 0 || count > maxTriangles)
                count = numTriangles

            val initial = mutableSetOf(triangleABC)
                val triangles = DivideTriangles.divideTriangles(initial, Math.pow( 4.0, count.toDouble()).toInt() )
                val triangleJs = triangles.joinToString("\n") {DrawTriangles.drawTriangle(it)}

                val html = """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <title>Triangles</title>
        </head>
        <body>
            <form action="/draw" method="get">
                <label>Dividion depth: 
                  <input name="count" type="number" value="$count">
                </label>
                <button type="submit">Divide triangle</button>
            </form>
            <p>The triangles are drawn</p>

            <canvas id="canvas" width="$width" height="$height" style="border:1px solid black;"></canvas>
            <script>
                const canvas = document.getElementById('canvas');
                const ctx = canvas.getContext('2d');

                $triangleJs
            </script>
        </body>
        </html>
    """.trimIndent()

                call.respondText(html, ContentType.Text.Html)
            }
        }
    }

