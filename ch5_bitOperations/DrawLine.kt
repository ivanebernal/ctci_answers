import kotlin.experimental.inv
import kotlin.experimental.or
import kotlin.experimental.and

fun main(args: Array<String>) {
    if(args.size != 3) throw Exception("Must provide 3 arguments: x start of line, x end of line, and y")
    val screen = ByteArray(8*20) { 0xFF.toByte() }
    val x1 = args[0].toInt()
    val x2 = args[1].toInt()
    val y = args[2].toInt()
    val width = 64
    drawLine(screen, width, x1, x2, y)
    printScreen(screen, width)
}

fun drawLine(screen: ByteArray, width: Int, x1: Int, x2: Int, y: Int) {
    if(x1 < 0 || x2 < 0 || x1 >= width || x2 >= width || y * width / 8 > screen.size) throw Exception("Out of bounds")
    
    val start = y*width + x1
    val end = y*width + x2
    
    val startFrom = start % 8
    val startByte = if(startFrom == 0) start / 8 else (start / 8) + 1
    
    val endByte = end / 8
    val endTo = end % 8
    
    screen[start / 8] = screen[start / 8].and(((1 shl startFrom) - 1).toByte())
    screen[endByte] = screen[endByte].and(((1 shl (8 - endTo)) - 1).toByte().inv())

    for(i in startByte until endByte) {
        screen[i] = 0.toByte()
    }
}

fun printScreen(screen: ByteArray, width: Int) {
    for((i,byte) in screen.withIndex()) {
        if(i > 0 && (i*8) % width == 0) println()
        var count = 0
        var temp = byte
        while(count < 8) { //>
            if(temp and 1 == 1.toByte()) print(".") else print(" ")
            temp = (temp.toInt() shr 1).toByte()
            count++
        }
    }
    println()
}