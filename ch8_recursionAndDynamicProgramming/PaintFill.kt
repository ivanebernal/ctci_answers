fun main(args: Array<String>) {
    if(args.size != 3) throw Exception("Provide three arguments: color, x and y")
    val color = args[0].toInt(16)
    val column = args[1].toInt()
    val row = args[2].toInt()
    val red = 0xFF0000
    val green = 0x00FF00
    val blue = 0x0000FF
    if(color != red && color != blue && color != green){
        throw Exception("Sorry, only Green, Red, and Blue supported")
    }
    val screen = arrayOf(
        arrayOf(red, blue, red, red, red, blue, red),
        arrayOf(blue, blue, red, blue, red, blue, blue),
        arrayOf(blue, blue, red, red, red, blue, blue),
        arrayOf(red, blue, blue, blue, blue, red, red),
        arrayOf(blue, blue, red, blue, blue, blue, blue)
    )
    printColoredScreen(screen)
    paintFill(screen, color, Pair(row, column))
    println("-----------------------------")
    printColoredScreen(screen)
}

fun paintFill(screen: Array<Array<Int>>, color: Int, point: Pair<Int, Int>) {
    if(point.first >= screen.size || point.second > screen[0].size) throw Exception("Point outside screen bounds")
    paintFillHelper(screen, color, point)
}

fun paintFillHelper(screen: Array<Array<Int>>, color: Int, point: Pair<Int, Int>) {
    if(screen[point.first][point.second] == color) {
        return
    } else {
        screen[point.first][point.second] = color
        val top = Pair(point.first - 1, point.second)
        val bottom = Pair(point.first + 1, point.second)
        val right = Pair(point.first, point.second + 1)
        val left = Pair(point.first, point.second - 1)
        if(point.first > 0) {
            paintFillHelper(screen, color, top)
        }
        if(point.first < screen.size - 1) { //>
            paintFillHelper(screen, color, bottom)
        }
        if(point.second > 0) {
            paintFillHelper(screen, color, left)
        }
        if(point.second < screen[0].size - 1) { //>
            paintFillHelper(screen, color, right)
        }
    }
}

fun printColoredScreen(screen: Array<Array<Int>>) {
    val red = 0xFF0000
    val green = 0x00FF00
    val blue = 0x0000FF
    val redbg = "\u001b[41m"
    val greenbg = "\u001b[42m"
    val bluebg = "\u001b[44m"
    screen.forEach { row ->
        row.forEach { color ->
            val bg = when(color) {
                red -> redbg
                green -> greenbg
                blue -> bluebg
                else -> throw Exception("Sorry, only Green, Red, and Blue supported")
            }
            print("$bg \u001b[0m")
        }
        println()
    }
}